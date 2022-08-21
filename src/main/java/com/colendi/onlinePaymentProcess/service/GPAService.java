package com.colendi.onlinePaymentProcess.service;

import com.colendi.onlinePaymentProcess.dto.GPADTO;
import com.colendi.onlinePaymentProcess.dto.UserDTO;

public interface GPAService {

    UserDTO addBalance(Long userId, GPADTO gpadto) throws Exception;
}
