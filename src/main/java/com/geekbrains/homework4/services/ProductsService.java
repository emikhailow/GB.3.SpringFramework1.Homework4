package com.geekbrains.homework4.services;

import com.geekbrains.homework4.data.Product;
import com.geekbrains.homework4.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getProductsList() {
        return productsRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }
}
