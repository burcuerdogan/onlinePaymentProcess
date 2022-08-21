package com.colendi.onlinePaymentProcess.dto;

import com.colendi.onlinePaymentProcess.entity.Card;
import com.colendi.onlinePaymentProcess.entity.GPA;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private GPA gpa;

    private List<Card> cards;
}
