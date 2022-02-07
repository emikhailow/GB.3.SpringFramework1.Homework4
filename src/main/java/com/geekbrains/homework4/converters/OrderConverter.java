package com.geekbrains.homework4.converters;

import com.geekbrains.homework4.dto.OrderDto;
import com.geekbrains.homework4.dto.OrderItemDto;
import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order){
        return new OrderDto(order.getId(),
                order.getUser().getUsername(),
                order.getItems().stream()
                        .map(orderItemConverter::entityToDto)
                        .collect(Collectors.toList()),
                order.getTotalPrice(),
                order.getAddress(),
                order.getPhone());
    }
}
