package com.geekbrains.homework4.controllers;

import com.geekbrains.homework4.entities.Product;
import com.geekbrains.homework4.dto.ProductDto;
import com.geekbrains.homework4.exceptions.ResourceNotFoundException;
import com.geekbrains.homework4.services.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v13/products")
public class MainController {
    private ProductsService productsService;

    public MainController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public Page<ProductDto> getProductsList(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ){
        if(page < 1){
            page = 1;
        }
        return productsService.find(minPrice, maxPrice, titlePart, page)
                .map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return new ProductDto(productsService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product %d not found", id))));
    }

    @PostMapping
    public Product addItem(@RequestBody ProductDto productDto){
        Product product = new Product(productDto);
        product.setId(null);
        return productsService.addItem(product);
    }

    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable Long id){
        productsService.removeItem(id);
    }


}
