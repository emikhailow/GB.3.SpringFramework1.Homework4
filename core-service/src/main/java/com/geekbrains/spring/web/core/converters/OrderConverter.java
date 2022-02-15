package com.geekbrains.spring.web.core.converters;

import com.geekbrains.spring.web.core.dto.OrderDto;
import com.geekbrains.spring.web.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order){
        return new OrderDto(order.getId(),
                order.getUsername(),
                order.getItems().stream()
                        .map(orderItemConverter::entityToDto)
                        .collect(Collectors.toList()),
                order.getTotalPrice(),
                order.getAddress(),
                order.getPhone());
    }
}
