package com.colendi.onlinePaymentProcess.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CardTransactionResponse {
    private Long id;

    private Long userId;

    private Long cardId;

    private Long merchantId;

    private Double amount;

    private Timestamp createdDate;
}
