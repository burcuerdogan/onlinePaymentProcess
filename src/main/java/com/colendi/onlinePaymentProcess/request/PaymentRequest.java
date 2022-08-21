package com.colendi.onlinePaymentProcess.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
    private Double spendAmount;
    private Long merchantId;
    private Long cardId;
}
