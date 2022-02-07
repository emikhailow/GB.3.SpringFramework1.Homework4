package com.geekbrains.homework4.dto;

import com.geekbrains.homework4.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private long id;

    public OrderDto(Order order) {
        this.id = order.getId();
    }
}
