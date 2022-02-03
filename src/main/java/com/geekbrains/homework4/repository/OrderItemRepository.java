package com.geekbrains.homework4.repository;

import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
