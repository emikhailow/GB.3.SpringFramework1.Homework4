package com.geekbrains.spring.web.api.core;

public class OrderDetailsDto {
    private String address;
    private String phone;

    public OrderDetailsDto(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public OrderDetailsDto() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
