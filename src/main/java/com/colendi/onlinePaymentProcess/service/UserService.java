package com.colendi.onlinePaymentProcess.service;

import com.colendi.onlinePaymentProcess.dto.CardDTO;
import com.colendi.onlinePaymentProcess.dto.CardTransactionDTO;
import com.colendi.onlinePaymentProcess.dto.PaymentDTO;
import com.colendi.onlinePaymentProcess.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<CardDTO> getCardsByUserId(Long userId);

    UserDTO createCard(Long userId, CardDTO cardDTO) throws Exception;

    UserDTO reissueCard(Long userId, Long cardId) throws Exception;

    UserDTO findById(Long userId) throws Exception;

    CardTransactionDTO spendFromCard(Long userId, PaymentDTO paymentDTO) throws Exception;
}
