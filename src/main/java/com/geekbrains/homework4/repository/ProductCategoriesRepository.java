package com.geekbrains.homework4.repository;

import com.geekbrains.homework4.entities.Product;
import com.geekbrains.homework4.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategory, Long>, JpaSpecificationExecutor<Product> {
}
