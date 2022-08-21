package com.colendi.onlinePaymentProcess.repository;

import com.colendi.onlinePaymentProcess.entity.CardTransaction;
import org.springframework.data.repository.CrudRepository;

public interface CardTransactionRepository extends CrudRepository<CardTransaction, Long> {
}
