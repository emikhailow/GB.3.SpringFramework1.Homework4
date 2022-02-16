package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.dto.CartDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.dto.OrderDetailsDto;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final ProductsService productsService;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto){

        HttpHeaders headers = new HttpHeaders();
        headers.set("username", username);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<CartDto> response = restTemplate.exchange(String.format("http://localhost:5555/cart/api/v1/cart/%s", "none"),
                HttpMethod.GET,
                requestEntity, CartDto.class);

        CartDto currentCart = response.getBody();

//        String cartKey = cartService.getCartUuidFromSuffix(username);
//        Cart currentCart = cartService.getCurrentCart(cartKey);

        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
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
        restTemplate.getForObject(
                String.format("http://localhost:5555/cart/api/v1/cart/%s/clear", username),
                CartDto.class);

        //cartService.clearCart(cartKey);
    }

    public List<Order> findOrdersByUsername(String username){
        return ordersRepository.findAllByUsername(username);
    }

}
