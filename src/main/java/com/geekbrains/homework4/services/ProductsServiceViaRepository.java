package com.geekbrains.homework4.services;

import com.geekbrains.homework4.data.Product;
import com.geekbrains.homework4.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceViaRepository implements ProductsService {
    private ProductsRepository productsRepository;

    public ProductsServiceViaRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getProductsList() {
        return productsRepository.getProductsList();
    }

    public void removeItem(Long id) {
        productsRepository.removeItem(id);
    }

    public void addItem(String title) {
        productsRepository.addItem(title);
    }
}
