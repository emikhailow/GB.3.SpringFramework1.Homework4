package com.geekbrains.homework4.data;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {
    Product getByID(Long id);
    List<Product> getProductsList();
    void removeItem(Long id);
    void addItem(String title);
}
