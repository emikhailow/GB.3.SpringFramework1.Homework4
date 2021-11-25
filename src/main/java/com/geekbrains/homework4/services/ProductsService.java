package com.geekbrains.homework4.services;

import com.geekbrains.homework4.data.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductsService {

    List<Product> getProductsList();
    void removeItem(Long id);
    void addItem(String title);

}
