package com.colendi.onlinePaymentProcess.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LimitScopeRequest {

    private Long cardId;

    private String mcc;

    private Long mid;
}
