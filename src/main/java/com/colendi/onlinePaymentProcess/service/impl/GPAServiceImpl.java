package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.GPADTO;
import com.colendi.onlinePaymentProcess.dto.UserDTO;
import com.colendi.onlinePaymentProcess.entity.GPA;
import com.colendi.onlinePaymentProcess.repository.GPARepository;
import com.colendi.onlinePaymentProcess.repository.UserRepository;
import com.colendi.onlinePaymentProcess.service.GPAService;
import com.colendi.onlinePaymentProcess.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class GPAServiceImpl implements GPAService {

    @Autowired
    private UserService userService;
    @Autowired
    private GPARepository gpaRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public UserDTO addBalance(Long userId, GPADTO gpadto) throws Exception {
        UserDTO userDTO = userService.findById(userId);
        Optional<GPA> gpa = gpaRepository.findById(userDTO.getGpa().getId());
        if(gpa.isPresent()) {
            gpa.get().setBalance(gpa.get().getBalance() == null ? 0 : gpa.get().getBalance() + gpadto.getBalance());
            gpaRepository.save(gpa.get());
        }
        return userService.findById(userId);
    }

    @Override
    @Transactional
    public Double loadBalance(GPADTO gpadto, double amount) throws Exception {
        double newBalance = gpadto.getBalance() + amount;
        gpadto.setBalance(newBalance);
        gpaRepository.save(mapper.map(gpadto, GPA.class));
        return newBalance;
    }

    @Override
    @Transactional
    public Double decreaseBalance(GPADTO gpadto, double amount) throws Exception {
        double newBalance = gpadto.getBalance() - amount;
        gpadto.setBalance(newBalance);
        gpaRepository.save(mapper.map(gpadto, GPA.class));
        return newBalance;
    }
}
