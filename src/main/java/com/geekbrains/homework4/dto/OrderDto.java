package com.geekbrains.homework4.dto;

import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {
    private long id;
    private String username;
    private List<OrderItemDto> items;
    private Integer totalPrice;
    private String address;
    private String phone;
}
