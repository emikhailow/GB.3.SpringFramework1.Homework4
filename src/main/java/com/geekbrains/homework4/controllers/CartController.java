package com.geekbrains.homework4.controllers;

import com.geekbrains.homework4.dto.Cart;
import com.geekbrains.homework4.dto.OrderDto;
import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.User;
import com.geekbrains.homework4.services.CartService;
import com.geekbrains.homework4.services.OrderService;
import com.geekbrains.homework4.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v13/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cartService.addProductByIdToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clear();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(Principal principal){

        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        Order order = orderService.saveOrder(cartService.getCurrentCart(), user);

        return ResponseEntity.ok(new OrderDto(order));
    }
}
