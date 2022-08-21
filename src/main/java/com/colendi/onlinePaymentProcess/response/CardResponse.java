package com.colendi.onlinePaymentProcess.response;

import com.colendi.onlinePaymentProcess.dto.MerchantDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CardResponse {
    private Long id;

    private String expiryDate;

    private String cvv;

    private Boolean isCancelled;

    private List<MerchantDTO> merchants;
}
