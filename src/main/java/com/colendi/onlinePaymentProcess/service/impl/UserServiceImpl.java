package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.*;
import com.colendi.onlinePaymentProcess.entity.Card;
import com.colendi.onlinePaymentProcess.entity.GPA;
import com.colendi.onlinePaymentProcess.entity.Merchant;
import com.colendi.onlinePaymentProcess.entity.User;
import com.colendi.onlinePaymentProcess.repository.UserRepository;
import com.colendi.onlinePaymentProcess.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CardTransactionService cardTransactionService;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<CardDTO> getCardsByUserId(Long userId) {
        List<CardDTO> cardDTOList = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && !user.get().getCards().isEmpty() ) {
            cardDTOList = user.get().getCards().stream()
                    .map(card -> mapper.map(card, CardDTO.class)).collect(Collectors.toList());
        }

        return cardDTOList;
    }

    @Override
    public UserDTO createCard(Long userId, CardDTO cardDTO) throws Exception {
        Card card = mapper.map(cardDTO, Card.class);
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {//TODO validation
            user.get().getCards().add(card);
            userRepository.save(user.get());
            return mapper.map(user.get(), UserDTO.class);
        }else {
            throw new Exception("User not found");
        }

    }

    @Override
    @Transactional
    public UserDTO reissueCard(Long userId, Long cardId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        CardDTO card = cardService.getCardById(cardId);
        Card reissuedCard = new Card();
        // TODO: util class'ı oluşturup içerisine generate expiryDate ve cvv oluşturmayı taşıyacağız.
        if(user.isPresent() && card != null) {
            Date expiryDate = Date.valueOf(LocalDate.now().plusYears(1));
            reissuedCard.setExpiryDate(expiryDate);
            reissuedCard.setMerchants(card.getMerchants().stream().map(m -> mapper.map(m, Merchant.class))
                    .collect(Collectors.toSet()));
            reissuedCard.setCvv(String.format("%3d",new Random().nextInt(1000)));
            reissuedCard.setIsCancelled(false);
            Set<Card> cards = user.get().getCards();
            cards.add(reissuedCard);
            cardService.cancelCard(cardId);
            userRepository.save(user.get());
            return mapper.map(user.get(), UserDTO.class);
        } else {
            throw new Exception("User or card does not exist");
        }
    }

    @Override
    public UserDTO findById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return mapper.map(user, UserDTO.class);
        } else {
            throw new Exception("User does not exist");
        }
    }

    @Override
    @Transactional
    public CardTransactionDTO spendFromCard(Long userId, PaymentDTO paymentDTO) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            if (user.get().getGpa() == null && paymentDTO.getSpendAmount() > user.get().getGpa().getBalance()){
                throw new Exception("User's balance is insufficient");
            }
            MerchantDTO merchantDTO = merchantService.getById(paymentDTO.getMerchantId());
            Merchant merchant = mapper.map(merchantDTO, Merchant.class);
            CardDTO card = cardService.getCardById(paymentDTO.getCardId());

            if(card.getMerchants().stream().noneMatch(x -> x.getMcc().equals(merchant.getMcc()))) {
                throw new Exception("This card is not authorized for this merchant");
            }
            GPA userGPA = user.get().getGpa();
            userGPA.setBalance(userGPA.getBalance() - paymentDTO.getSpendAmount());

            GPA merchantGPA =  merchant.getGpa();
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
