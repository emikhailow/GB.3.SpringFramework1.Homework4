package com.geekbrains.spring.web.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Order model")
public class OrderDto {
    @Schema(description = "Order ID", required = true, example = "1")
    private long id;
    @Schema(description = "Name of the user who created order", required = true, example = "bob")
    private String username;
    @Schema(description = "List of items in order", required = true)
    private List<OrderItemDto> items;
    @Schema(description = "Total price", required = true)
    private BigDecimal totalPrice;
    @Schema(description = "Address of order's owner", required = true)
    private String address;
    @Schema(description = "Phone number of order's owner", required = true)
    private String phone;

    public OrderDto(long id, String username, List<OrderItemDto> items, BigDecimal totalPrice, String address, String phone) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
