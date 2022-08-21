package com.colendi.onlinePaymentProcess.api;

import com.colendi.onlinePaymentProcess.request.CardRequest;
import com.colendi.onlinePaymentProcess.request.GPARequest;
import com.colendi.onlinePaymentProcess.request.LimitScopeRequest;
import com.colendi.onlinePaymentProcess.request.PaymentRequest;
import com.colendi.onlinePaymentProcess.response.CardResponse;
import com.colendi.onlinePaymentProcess.response.CardTransactionResponse;
import com.colendi.onlinePaymentProcess.response.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api")
public interface OnlinePaymentProcessApi {

    @GetMapping(value = "/getCardById/{cardId}")
    CardResponse getCardById(@PathVariable Long cardId) throws Exception;

    @GetMapping(value = "/getCardsByUserId/{userId}")
    List<CardResponse> getCardsByUserId(@PathVariable Long userId);

    @PostMapping(value = "/createCard/{userId}")
    UserResponse createCard(@PathVariable Long userId, @RequestBody @Validated CardRequest cardRequest) throws Exception;

    @GetMapping(value = "/reissueCard/{userId}/{cardId}")
    UserResponse reissueCard(@PathVariable Long userId, @PathVariable Long cardId) throws Exception;

    @GetMapping(value = "/cancelCard/{cardId}")
    CardResponse cancelCard(@PathVariable Long cardId) throws Exception;//TODO

    @PostMapping(value = "/addLimitScope/")
    CardResponse addLimitScope(@RequestBody LimitScopeRequest limitScopeRequest) throws Exception;

    @PostMapping(value = "/addBalanceToUserGPA/{userId}")
    UserResponse addBalanceToUserGPA(@PathVariable Long userId, @RequestBody GPARequest gpaRequest) throws Exception;

    @PostMapping(value = "/spendFromCard/{userId}")
    CardTransactionResponse spendFromCard(@PathVariable Long userId, @RequestBody PaymentRequest paymentRequest) throws Exception;

}
