package com.geekbrains.homework4.services;

import com.geekbrains.homework4.dto.Cart;
import com.geekbrains.homework4.entities.Product;
import com.geekbrains.homework4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductsService productsService;
    private Cart cart;

    @PostConstruct
    public void init(){
        this.cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void addProductByIdToCart(Long productId){
        if(!getCurrentCart().addProduct(productId)){
            Product product = productsService.getProductById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", productId)));
            getCurrentCart().addProduct(product);
        }
    }

    public void clear(){
        getCurrentCart().clear();
    }

}
