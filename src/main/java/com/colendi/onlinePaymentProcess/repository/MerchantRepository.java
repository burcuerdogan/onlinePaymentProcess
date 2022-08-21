package com.colendi.onlinePaymentProcess.repository;

import com.colendi.onlinePaymentProcess.entity.Merchant;
import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
}
