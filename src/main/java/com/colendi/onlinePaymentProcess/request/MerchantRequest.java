package com.colendi.onlinePaymentProcess.request;

import com.colendi.onlinePaymentProcess.dto.GPADTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MerchantRequest {

    private String mcc;

    private GPADTO gpa;
}
