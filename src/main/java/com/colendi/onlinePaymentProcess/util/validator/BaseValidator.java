package com.colendi.onlinePaymentProcess.util.validator;

public interface BaseValidator<T> {

    void validator(T object) throws Exception;
}
