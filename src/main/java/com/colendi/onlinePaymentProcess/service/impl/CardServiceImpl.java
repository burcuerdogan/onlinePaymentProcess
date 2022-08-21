package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.CardDTO;
import com.colendi.onlinePaymentProcess.dto.LimitScopeDTO;
import com.colendi.onlinePaymentProcess.dto.MerchantDTO;
import com.colendi.onlinePaymentProcess.entity.Card;
import com.colendi.onlinePaymentProcess.entity.Merchant;
import com.colendi.onlinePaymentProcess.entity.User;
import com.colendi.onlinePaymentProcess.repository.CardRepository;
import com.colendi.onlinePaymentProcess.repository.UserRepository;
import com.colendi.onlinePaymentProcess.response.CardResponse;
import com.colendi.onlinePaymentProcess.service.CardService;
import com.colendi.onlinePaymentProcess.service.MerchantService;
import com.colendi.onlinePaymentProcess.util.validator.CardValidator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CardServiceImpl implements CardService {

    private final static Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CardValidator cardValidator;

    @Override
    public CardDTO getCardById(Long cardId) throws Exception {
        logger.info("Card get request for {}", cardId);
        Optional<Card> card = cardRepository.findById(cardId);

        assert card.isPresent();
        cardValidator.validator(card.get());

        CardDTO cardDTO = mapper.map(card.get(), CardDTO.class);

        return cardDTO;

    }

    @Override
    public CardDTO cancelCard(Long cardId) throws Exception {
        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isPresent() && !card.get().getIsCancelled()) {
            card.get().setIsCancelled(true);
            cardRepository.save(card.get());
            return mapper.map(card.get(), CardDTO.class);
        } else {
            throw new Exception("Active card is not found");
        }
    }

    @Override
    public CardDTO addLimitScope(LimitScopeDTO limitScopeDTO) throws Exception {
        Optional<Card> card = cardRepository.findById(limitScopeDTO.getCardId());
        if (card.isPresent()) {
            Set<Merchant> merchants = card.get().getMerchants();
            MerchantDTO merchant = merchantService.getById(limitScopeDTO.getMid());
            merchants.add(mapper.map(merchant, Merchant.class));
            card.get().setMerchants(merchants);
            cardRepository.save(card.get());
            return mapper.map(card.get(), CardDTO.class);
        } else {
            throw new Exception("Card does not exist");
        }
    }

}
