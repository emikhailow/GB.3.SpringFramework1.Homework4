package com.geekbrains.spring.web.api.dto;

public class StringResponse {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public StringResponse(String value) {
        this.value = value;
    }

    public StringResponse() {
    }
}
