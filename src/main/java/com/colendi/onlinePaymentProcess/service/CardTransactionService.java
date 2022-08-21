package com.colendi.onlinePaymentProcess.service;

import com.colendi.onlinePaymentProcess.dto.CardTransactionDTO;

public interface CardTransactionService {

    CardTransactionDTO createCardTransaction(CardTransactionDTO cardTransactionDTO);
}
