package com.colendi.onlinePaymentProcess.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MerchantDTO {

    private Long id;

    private String mcc;

    private GPADTO gpa;
}
