package com.geekbrains.homework4.data;

import com.geekbrains.homework4.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    List<Product> productList;

    public Cart(List<Product> productList) {
        this.productList = productList;
    }

    public Cart() {
        this.productList = new ArrayList<>();
    }

    public Product addItem(Product product){
        if(!productList.contains(product)){
            productList.add(product);
        }
        return product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
