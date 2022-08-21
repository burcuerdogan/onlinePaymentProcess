package com.colendi.onlinePaymentProcess.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {

    private Long id;

    private String expiryDate;

    private String cvv;

    private Boolean isCancelled;

    private List<MerchantDTO> merchants;
}
