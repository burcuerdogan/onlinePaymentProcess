package com.colendi.onlinePaymentProcess.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CardTransactionDTO {
    private Long id;

    private Long userId;

    private Long cardId;

    private Long merchantId;

    private Double amount;

    private Timestamp createdDate;
}
