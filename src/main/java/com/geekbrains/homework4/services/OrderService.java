package com.geekbrains.homework4.services;

import com.geekbrains.homework4.dto.Cart;
import com.geekbrains.homework4.dto.OrderDto;
import com.geekbrains.homework4.dto.OrderItemDto;
import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.OrderItem;
import com.geekbrains.homework4.entities.User;
import com.geekbrains.homework4.exceptions.ResourceNotFoundException;
import com.geekbrains.homework4.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductsService productsService;

    public Order saveOrder(Cart cart, User user){

        Order order = new Order();
        order.setUser(user);
        for (OrderItemDto orderItemDto : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(orderItemDto.getPrice());
            orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
            orderItem.setUser(order.getUser());
            orderItem.setProduct(productsService.getProductById(orderItemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", orderItemDto.getProductId()))));
            order.addItem(orderItem);
        }
        order.setTotalPrice(cart.getTotalPrice());
        orderRepository.save(order);

        return order;

    }

}
