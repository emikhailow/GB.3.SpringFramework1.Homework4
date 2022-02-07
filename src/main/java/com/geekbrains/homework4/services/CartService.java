package com.geekbrains.homework4.services;

import com.geekbrains.homework4.data.Cart;
import com.geekbrains.homework4.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private Cart cart;
    public ProductsService productsService;

    public CartService(ProductsService productsService, Cart cart) {
        this.cart = cart;
        this.productsService = productsService;
    }

    public Product addItem(Long id){
        return cart.addItem(productsService.getProductById(id).orElseThrow());
    }

    public Cart getCart() {
        return cart;
    }
}
