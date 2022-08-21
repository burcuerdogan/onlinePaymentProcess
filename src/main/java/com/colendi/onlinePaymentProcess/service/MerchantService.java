package com.colendi.onlinePaymentProcess.service;

import com.colendi.onlinePaymentProcess.dto.MerchantDTO;

import java.util.List;

public interface MerchantService {
    List<MerchantDTO> getMerchantList(List<Long> merchantIds);

    MerchantDTO getById(Long merchantId) throws Exception;

    MerchantDTO loadBalance(Long merchantId, Double amount) throws Exception;
}
