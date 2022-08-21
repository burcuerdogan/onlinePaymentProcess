package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.CardTransactionDTO;
import com.colendi.onlinePaymentProcess.entity.CardTransaction;
import com.colendi.onlinePaymentProcess.repository.CardTransactionRepository;
import com.colendi.onlinePaymentProcess.service.CardTransactionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CardTransactionServiceImpl implements CardTransactionService {
    private final static Logger logger = LoggerFactory.getLogger(CardTransactionServiceImpl.class);

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public CardTransactionDTO createCardTransaction(CardTransactionDTO cardTransactionDTO) {
        logger.info("Create card transaction request");

        cardTransactionRepository.save(mapper.map(cardTransactionDTO, CardTransaction.class));
        return cardTransactionDTO;
    }
}
