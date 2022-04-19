package com.geekbrains.spring.web.core;

import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.repository.OrdersRepository;
import com.geekbrains.spring.web.core.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class CoreServiceTest1 {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrdersRepository ordersRepository;

    @Test
    public void checkIfOrderIsPaid(){

        Order order = new Order();
        order.setId(5L);
        order.setStatus("PAID");

        Mockito.doReturn(Optional.of(order))
                .when(ordersRepository)
                .findById(5L);

        Assertions.assertTrue(orderService.isPaid(5L));

    }

}
