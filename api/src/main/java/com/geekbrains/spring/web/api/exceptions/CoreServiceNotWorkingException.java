package com.geekbrains.spring.web.api.exceptions;

public class CoreServiceNotWorkingException extends RuntimeException{
    public CoreServiceNotWorkingException(String message) {
        super(message);
    }
}
