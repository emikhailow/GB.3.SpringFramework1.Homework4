package com.geekbrains.homework4.controllers;

import com.geekbrains.homework4.converters.ProductCategoryConverter;
import com.geekbrains.homework4.dto.ProductCategoryDto;
import com.geekbrains.homework4.entities.Product;
import com.geekbrains.homework4.dto.ProductDto;
import com.geekbrains.homework4.exceptions.ResourceNotFoundException;
import com.geekbrains.homework4.repository.ProductCategoriesRepository;
import com.geekbrains.homework4.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v15/products")
@RequiredArgsConstructor
public class MainController {
    private final ProductsService productsService;
    private final ProductCategoryConverter productCategoryConverter;

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
                .map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return new ProductDto(productsService.findById(id)
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
