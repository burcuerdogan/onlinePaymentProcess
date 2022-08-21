package com.colendi.onlinePaymentProcess.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {
    private Double spendAmount;
    private Long merchantId;
    private Long cardId;
}
