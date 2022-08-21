package com.colendi.onlinePaymentProcess.response;

import com.colendi.onlinePaymentProcess.dto.GPADTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MerchantResponse {
    private String mcc;

    private GPADTO gpa;
}
