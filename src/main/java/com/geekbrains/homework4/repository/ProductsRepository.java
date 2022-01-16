package com.geekbrains.homework4.repository;

import com.geekbrains.homework4.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    //List<Product> findAllByCostBetween(Integer min, Integer max);
}
