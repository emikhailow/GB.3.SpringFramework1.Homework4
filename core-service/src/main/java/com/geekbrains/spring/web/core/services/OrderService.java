package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.core.converters.ProductsConverter;
import com.geekbrains.spring.web.core.data.OrderStatuses;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.core.repository.OrderItemRepository;
import com.geekbrains.spring.web.core.repository.OrdersRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductsService productsService;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductsConverter productsConverter;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto){

        CartDto currentCart = cartServiceIntegration.getUserCart(username);

        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        order.setStatus(OrderStatuses.CREATED.name());

        order.setAddressLine1(orderDetailsDto.getAddressLine1());
        order.setAddressLine2(orderDetailsDto.getAddressLine2());
        order.setAdminArea1(orderDetailsDto.getAdminArea1());
        order.setAdminArea2(orderDetailsDto.getAdminArea2());
        order.setPostalCode(orderDetailsDto.getPostalCode());
        order.setCountryCode(orderDetailsDto.getCountryCode());

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
        cartServiceIntegration.clearUserCart(username);

    }

    public List<Order> findOrdersByUsername(String username){
        return ordersRepository.findAllByUsername(username);
    }

    public List<ProductDto> getMostOrderedItems(Integer count){
        return orderItemRepository.findMostOrderedItems(PageRequest.of(0, count)).stream()
                .map(productsConverter::entityToDto)
                .collect(Collectors.toList());
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public void changeStatusToPaid(Long id){
        Order order = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id %d not found", id)));
        order.setStatus(OrderStatuses.PAID.name());
        ordersRepository.save(order);
    }

    public boolean isPaid(Long id){
        return ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id %d not found", id)))
                .getStatus()
                .equals(OrderStatuses.PAID.name());
    }
}
