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

    private String addressLine1;
    private String addressLine2;
    private String adminArea1;
    private String adminArea2;
    private String postalCode;
    private String countryCode;

    private String status;

    public OrderDto(long id, String username, List<OrderItemDto> items, BigDecimal totalPrice, String address, String phone, String addressLine1, String addressLine2, String adminArea1, String adminArea2, String postalCode, String countryCode, String status) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.adminArea1 = adminArea1;
        this.adminArea2 = adminArea2;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        this.status = status;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAdminArea1() {
        return adminArea1;
    }

    public void setAdminArea1(String adminArea1) {
        this.adminArea1 = adminArea1;
    }

    public String getAdminArea2() {
        return adminArea2;
    }

    public void setAdminArea2(String adminArea2) {
        this.adminArea2 = adminArea2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
