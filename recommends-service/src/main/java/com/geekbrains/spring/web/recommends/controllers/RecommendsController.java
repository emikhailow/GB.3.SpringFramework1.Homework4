package com.geekbrains.spring.web.recommends.controllers;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.recommends.services.RecommendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecommendsController {
    private final RecommendsService recommendsService;

    @GetMapping("/most-ordered")
    public List<ProductDto> getMostOrderedItems(@RequestParam(name = "count", defaultValue = "5") Integer count){
        return recommendsService.getMostOrderedItems(count);
    }

    @GetMapping("/most-added-to-cart")
    public List<ProductDto> getMostAddedToCartItems(@RequestParam(name = "count", defaultValue = "5") Integer count){
        return recommendsService.getMostAddedToCartItems(count);
    }

}