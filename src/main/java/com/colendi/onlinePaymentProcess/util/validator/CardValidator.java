package com.colendi.onlinePaymentProcess.util.validator;

import com.colendi.onlinePaymentProcess.entity.Card;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardValidator implements BaseValidator<Card> {

    @Override
    public void validator(Card card) throws Exception {
        if (Objects.isNull(card) && Objects.isNull(card.getId())){
            throw new Exception("Card or card id cannot be null");
        }
    }
}
