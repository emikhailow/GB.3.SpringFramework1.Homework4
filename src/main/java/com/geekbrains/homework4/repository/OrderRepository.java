package com.geekbrains.homework4.repository;

import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
