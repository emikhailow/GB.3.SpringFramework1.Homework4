package com.geekbrains.spring.web.core.repository;

import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select oi.product from OrderItem oi where month(current_date) = month(oi.createdAt) group by oi.product order by sum(oi.quantity) desc")
    List<Product> findMostOrderedItems(Pageable pageable);
}
