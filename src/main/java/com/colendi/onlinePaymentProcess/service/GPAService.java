package com.colendi.onlinePaymentProcess.service;

import com.colendi.onlinePaymentProcess.dto.GPADTO;
import com.colendi.onlinePaymentProcess.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface GPAService {

    UserDTO addBalance(Long userId, GPADTO gpadto) throws Exception;

    Double loadBalance(GPADTO gpadto, double amount) throws Exception;

    Double decreaseBalance(GPADTO gpadto, double amount) throws Exception;


}
