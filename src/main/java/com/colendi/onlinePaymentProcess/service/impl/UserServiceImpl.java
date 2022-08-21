package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.*;
import com.colendi.onlinePaymentProcess.entity.Card;
import com.colendi.onlinePaymentProcess.entity.GPA;
import com.colendi.onlinePaymentProcess.entity.Merchant;
import com.colendi.onlinePaymentProcess.entity.User;
import com.colendi.onlinePaymentProcess.repository.UserRepository;
import com.colendi.onlinePaymentProcess.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CardTransactionService cardTransactionService;

    private final ModelMapper mapper = new ModelMapper();
    private final static Integer CVVBOUND = 1000;
    private final static String CVVDIGITFORMAT = "%3d";

    @Override
    public List<CardDTO> getCardsByUserId(Long userId) {
        logger.info("Get cards by user id : {}", userId);

        List<CardDTO> cardDTOList = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && !user.get().getCards().isEmpty()) {
            cardDTOList = user.get().getCards().stream()
                    .map(card -> mapper.map(card, CardDTO.class)).collect(Collectors.toList());
        }

        return cardDTOList;
    }

    @Override
    public UserDTO createCard(Long userId, CardDTO cardDTO) throws Exception {
        logger.info("Create card by user id : {}", userId);

        Card card = mapper.map(cardDTO, Card.class);
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().getCards().add(card);
            userRepository.save(user.get());
            return mapper.map(user.get(), UserDTO.class);
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    @Transactional
    public UserDTO reissueCard(Long userId, Long cardId) throws Exception {
        logger.info("Reissue card by user id : {}", userId);

        Optional<User> user = userRepository.findById(userId);
        CardDTO card = cardService.getCardById(cardId);
        Card reissuedCard = new Card();
        if (user.isPresent() && Objects.nonNull(card)) {
            Date expiryDate = Date.valueOf(LocalDate.now().plusYears(1));

            reissuedCard.setExpiryDate(expiryDate);
            reissuedCard.setMerchants(card.getMerchants().stream().map(m -> mapper.map(m, Merchant.class))
                    .collect(Collectors.toSet()));
            reissuedCard.setCvv(String.format(CVVDIGITFORMAT, new Random().nextInt(CVVBOUND)));
            reissuedCard.setIsCancelled(false);

            Set<Card> cards = user.get().getCards();

            Optional<Card> userCard = cards.stream().filter(x -> x.getId().equals(cardId)).findFirst();
            if (!userCard.isPresent()) {
                throw new Exception("User's card does not exist");
            } else {
                userCard.get().setIsCancelled(true);
                cards.add(reissuedCard);
            }

            userRepository.save(user.get());
            return mapper.map(user.get(), UserDTO.class);
        } else {
            throw new Exception("User or card does not exist");
        }
    }

    @Override
    public UserDTO findById(Long userId) throws Exception {
        logger.info("Find user by id : {}", userId);

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return mapper.map(user.get(), UserDTO.class);
        } else {
            throw new Exception("User does not exist");
        }
    }

    @Override
    @Transactional
    public CardTransactionDTO spendFromCard(Long userId, PaymentDTO paymentDTO) throws Exception {
        logger.info("Spend from card, user id : {}", userId);

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            if (user.get().getGpa() == null && paymentDTO.getSpendAmount() > user.get().getGpa().getBalance()) {
                throw new Exception("User's balance is insufficient");
            }
            MerchantDTO merchantDTO = merchantService.getById(paymentDTO.getMerchantId());
            Merchant merchant = mapper.map(merchantDTO, Merchant.class);
            CardDTO card = cardService.getCardById(paymentDTO.getCardId());

            if (card.getMerchants().stream().noneMatch(x -> x.getMcc().equals(merchant.getMcc()))) {
                throw new Exception("This card is not authorized for this merchant");
            }
            GPA userGPA = user.get().getGpa();
            userGPA.setBalance(userGPA.getBalance() - paymentDTO.getSpendAmount());

            GPA merchantGPA = merchant.getGpa();
            merchantGPA.setBalance(merchantGPA.getBalance() + paymentDTO.getSpendAmount());

            CardTransactionDTO cardTransactionDTO = new CardTransactionDTO();
            cardTransactionDTO.setCardId(paymentDTO.getCardId());
            cardTransactionDTO.setAmount(paymentDTO.getSpendAmount());
            cardTransactionDTO.setUserId(userId);
            cardTransactionDTO.setMerchantId(paymentDTO.getMerchantId());

            userRepository.save(user.get());
            merchantService.loadBalance(paymentDTO.getMerchantId(), paymentDTO.getSpendAmount());
            cardTransactionService.createCardTransaction(cardTransactionDTO);

            return cardTransactionDTO;
        } else {
            throw new Exception("User is not exist");
        }
    }
}
