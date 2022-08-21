package com.colendi.onlinePaymentProcess.repository;

import com.colendi.onlinePaymentProcess.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
