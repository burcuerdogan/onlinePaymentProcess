package com.colendi.onlinePaymentProcess.request;

import com.colendi.onlinePaymentProcess.dto.MerchantDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CardRequest {

    @NotNull
    private String expiryDate;

    @Length(min = 3, max = 3)
    @NotNull
    private String cvv;

    private Boolean isCancelled;

    private List<Long> merchantIds;
}
