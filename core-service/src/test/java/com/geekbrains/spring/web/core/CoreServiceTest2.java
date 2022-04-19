package com.geekbrains.spring.web.core;

import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.core.repository.OrdersRepository;
import com.geekbrains.spring.web.core.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class CoreServiceTest2 {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void checkIfOrderIsSavedUnderCorrectUsername(){

        Order order = new Order();
        order.setUsername("bob");
        order.setTotalPrice(BigDecimal.ZERO);
        entityManager.persist(order);
        entityManager.flush();

        Assertions.assertEquals(2, ordersRepository.findAllByUsername("bob").size());

    }

}
