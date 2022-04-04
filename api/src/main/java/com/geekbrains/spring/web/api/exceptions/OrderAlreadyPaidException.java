package com.geekbrains.spring.web.api.exceptions;

public class OrderAlreadyPaidException extends RuntimeException{
    public OrderAlreadyPaidException(String message) {
        super(message);
    }
}

