package com.colendi.onlinePaymentProcess.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LimitScopeDTO {

    private Long cardId;

    private String mcc;

    private Long mid;
}
