package com.geekbrains.homework4.repository;

import com.geekbrains.homework4.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(Integer min, Integer max);
}
