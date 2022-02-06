package com.geekbrains.homework4.dto;

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
