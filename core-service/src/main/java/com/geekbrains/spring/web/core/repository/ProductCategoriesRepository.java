package com.geekbrains.spring.web.core.repository;

import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategory, Long>, JpaSpecificationExecutor<Product> {
}
