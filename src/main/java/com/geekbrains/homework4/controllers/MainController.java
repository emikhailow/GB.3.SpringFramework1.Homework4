package com.geekbrains.homework4.controllers;

import com.geekbrains.homework4.data.Product;
import com.geekbrains.homework4.services.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    private ProductsService productsService;

    public MainController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public List<Product> getProductsList(){
        return productsService.getProductsList();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productsService.getProductById(id).orElseThrow();
    }

    @GetMapping("products/remove/{id}")
    public void removeItem(@PathVariable Long id){
        productsService.removeItem(id);
    }

    @GetMapping("products/add/{title}")
    public void addItem(@PathVariable String title){
        //productsService.addItem(title);
    }

    @GetMapping("/products/cost_between")
    public List<Product> getProductsByCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max){
        return productsService.getProductsByCostBetween(min, max);
    }


}
