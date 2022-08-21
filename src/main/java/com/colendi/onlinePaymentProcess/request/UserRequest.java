package com.colendi.onlinePaymentProcess.request;

import com.colendi.onlinePaymentProcess.dto.CardDTO;
import com.colendi.onlinePaymentProcess.dto.GPADTO;
import com.colendi.onlinePaymentProcess.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    private String name;

    private GPADTO gpa;

    private List<CardDTO> cards;

}
