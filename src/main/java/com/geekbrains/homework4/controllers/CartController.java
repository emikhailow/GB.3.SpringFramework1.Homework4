package com.geekbrains.homework4.controllers;

import com.geekbrains.homework4.dto.Cart;
import com.geekbrains.homework4.dto.OrderDto;
import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.User;
import com.geekbrains.homework4.exceptions.AppError;
import com.geekbrains.homework4.services.CartService;
import com.geekbrains.homework4.services.OrderService;
import com.geekbrains.homework4.services.UserService;
import com.geekbrains.homework4.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<?> saveOrder(HttpServletRequest request){

        String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "You need to be authorized to save an order"), HttpStatus.UNAUTHORIZED);
        }
        jwt = authHeader.substring(7);

        User user = userService.getUserFromToken(jwt);
        Order order = orderService.saveOrder(cartService.getCurrentCart(), user);

        return ResponseEntity.ok(new OrderDto(order));
    }
}
