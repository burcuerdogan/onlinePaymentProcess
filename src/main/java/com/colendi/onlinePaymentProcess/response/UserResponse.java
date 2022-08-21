package com.colendi.onlinePaymentProcess.response;

import com.colendi.onlinePaymentProcess.dto.CardDTO;
import com.colendi.onlinePaymentProcess.dto.GPADTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private String name;

    private GPADTO gpa;

    private List<CardDTO> cards;

}
