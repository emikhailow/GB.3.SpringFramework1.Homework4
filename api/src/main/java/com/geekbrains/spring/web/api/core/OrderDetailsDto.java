package com.geekbrains.spring.web.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Order details model")
public class OrderDetailsDto {
    @Schema(description = "Order address", required = true, example = "Россия, Татарстан Республика, Казань Город, Братьев Касимовых Улица, дом 64")
    private String address;
    @Schema(description = "Order phone", required = true, example = "79123456789")
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
