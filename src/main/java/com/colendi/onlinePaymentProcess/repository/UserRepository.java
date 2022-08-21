package com.colendi.onlinePaymentProcess.repository;

import com.colendi.onlinePaymentProcess.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}