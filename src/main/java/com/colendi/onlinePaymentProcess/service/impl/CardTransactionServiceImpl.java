package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.CardTransactionDTO;
import com.colendi.onlinePaymentProcess.entity.CardTransaction;
import com.colendi.onlinePaymentProcess.repository.CardTransactionRepository;
import com.colendi.onlinePaymentProcess.service.CardTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CardTransactionServiceImpl implements CardTransactionService {
    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public CardTransactionDTO createCardTransaction(CardTransactionDTO cardTransactionDTO) {
        cardTransactionRepository.save(mapper.map(cardTransactionDTO, CardTransaction.class));
        return cardTransactionDTO;
    }
}
