package com.geekbrains.homework4.controllers;

import com.geekbrains.homework4.converters.OrderConverter;
import com.geekbrains.homework4.dto.OrderDetailsDto;
import com.geekbrains.homework4.dto.OrderDto;
import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.User;
import com.geekbrains.homework4.exceptions.ResourceNotFoundException;
import com.geekbrains.homework4.services.OrderService;
import com.geekbrains.homework4.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v15/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto){
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with username %s not found", principal.getName())));
        orderService.createOrder(user, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal){
        return orderService.findOrdersByUsername(principal.getName()).stream()
                .map(orderConverter::entityToDto)
                .collect(Collectors.toList());
    }

}
