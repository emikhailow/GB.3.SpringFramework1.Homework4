package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.repository.ProductsRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class ProductsCache {

    private final ProductsRepository productsRepository;
    private final HashMap<Long, Product> hashMap;

    public ProductsCache(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
        hashMap = new HashMap<>();
    }

    public Optional<Product> findById(Long id){

        Product product = hashMap.get(id);
        if(product == null){
            product = productsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found")));
            hashMap.put(id, product);
        }
        return Optional.of(product);
    }
}
