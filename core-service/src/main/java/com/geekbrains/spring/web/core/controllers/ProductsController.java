package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.converters.ProductCategoryConverter;
import com.geekbrains.spring.web.api.core.ProductCategoryDto;
import com.geekbrains.spring.web.core.converters.ProductsConverter;
import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.services.ProductsService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final ProductCategoryConverter productCategoryConverter;
    private final ProductsConverter productsConverter;

    @GetMapping
    public Page<ProductDto> getProductsList(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "category_id", defaultValue = "1") Long categorId,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ){
        if(page < 1){
            page = 1;
        }
        return productsService.find(minPrice, maxPrice, titlePart, page, categorId)
                .map(productsConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){

        return productsConverter.entityToDto(productsService.findById(id)
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

    @GetMapping("/categories")
    public List<ProductCategoryDto> getProductCategoriesList(){
       return productsService.findAllCategories().stream()
                .map(productCategoryConverter::entityToDto)
                .collect(Collectors.toList());
    }

}
