package com.geekbrains.homework4.services;

import com.geekbrains.homework4.data.Product;
import com.geekbrains.homework4.data.ProductDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProductsServiceViaDao implements ProductsService {
    private ProductDao productDao;

    public ProductsServiceViaDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getProductsList() {
       return productDao.getProductsList();
    }

    public void removeItem(Long id) {
        productDao.removeItem(id);
    }

    public void addItem(String title) {
        productDao.addItem(title);
    }
}
