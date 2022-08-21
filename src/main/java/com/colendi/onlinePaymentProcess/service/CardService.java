package com.colendi.onlinePaymentProcess.service;

import com.colendi.onlinePaymentProcess.dto.CardDTO;
import com.colendi.onlinePaymentProcess.dto.LimitScopeDTO;

public interface CardService {

    CardDTO getCardById(Long cardId) throws Exception;

    CardDTO cancelCard(Long cardId) throws Exception;

    CardDTO addLimitScope(LimitScopeDTO limitScopeDTO) throws Exception;
}
