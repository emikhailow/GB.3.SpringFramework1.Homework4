package com.geekbrains.homework4.controllers;

import com.geekbrains.homework4.dto.CartDto;
import com.geekbrains.homework4.dto.ProductDto;
import com.geekbrains.homework4.services.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v10/carts")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartDto getCart(){
        return new CartDto(cartService.getCart());
    }

    @PostMapping("/{id}")
    public ProductDto addItem(@PathVariable Long id){
        return new ProductDto(cartService.addItem(id));
    }
}
