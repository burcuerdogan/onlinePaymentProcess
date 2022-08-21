package com.colendi.onlinePaymentProcess.repository;

import com.colendi.onlinePaymentProcess.entity.Card;
import com.colendi.onlinePaymentProcess.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

}
