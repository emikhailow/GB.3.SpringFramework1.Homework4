package com.geekbrains.homework4.services;

import com.geekbrains.homework4.dto.Cart;
import com.geekbrains.homework4.dto.OrderDetailsDto;
import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.OrderItem;
import com.geekbrains.homework4.entities.User;
import com.geekbrains.homework4.exceptions.ResourceNotFoundException;
import com.geekbrains.homework4.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartService cartService;
    private final ProductsService productsService;

    @Transactional
    public void createOrder(User user, OrderDetailsDto orderDetailsDto){
        String cartKey = cartService.getCartUuidFromSuffix(user.getUsername());
        Cart currentCart = cartService.getCurrentCart(cartKey);

        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());

        List<OrderItem> items = currentCart.getItems().stream()
                .map(orderItemDto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setPrice(orderItemDto.getPrice());
                    orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
                    orderItem.setQuantity(orderItemDto.getQuantity());
                    orderItem.setProduct(productsService.findById(orderItemDto.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException(String.format("Item with id %d not found", orderItemDto.getProductId()))));
                    return orderItem;
                }).collect(Collectors.toList());
        order.setItems(items);
        ordersRepository.save(order);
        cartService.clearCart(cartKey);
    }

    public List<Order> findOrdersByUsername(String username){
        return ordersRepository.findAllByUsername(username);
    }

}
