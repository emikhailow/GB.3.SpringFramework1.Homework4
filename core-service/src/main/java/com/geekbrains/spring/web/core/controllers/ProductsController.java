package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.converters.ProductCategoryConverter;
import com.geekbrains.spring.web.api.core.ProductCategoryDto;
import com.geekbrains.spring.web.core.converters.ProductsConverter;
import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.services.ProductsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Method for operating with products")
public class ProductsController {
    private final ProductsService productsService;
    private final ProductCategoryConverter productCategoryConverter;
    private final ProductsConverter productsConverter;

    @Operation(
            summary = "Request to receive page of products",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public Page<ProductDto> getProductsList(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "category_id", defaultValue = "1") Long categorId,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productsService.find(minPrice, maxPrice, titlePart, page, categorId)
                .map(productsConverter::entityToDto);
    }

    @Operation(
            summary = "Request to get product by ID",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto getProductById(
            @PathVariable @Parameter(description = "Product ID", required = true) Long id
    ) {

        return productsConverter.entityToDto(productsService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product %d not found", id))));
    }

    @Operation(
            summary = "Request to get product by ID",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @PostMapping
    public void addItem(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product dto",
                    required = true
            )
            @RequestBody ProductDto productDto
    ) {
        Product product = new Product(productDto);
        product.setId(null);
        productsService.addItem(product);
    }

    @Operation(
            summary = "Request to remove item by ID",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable Long id) {
        productsService.removeItem(id);
    }

    @Operation(
            summary = "Request to get list of product categories",
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductCategoryDto.class)))
                    )
            }
    )
    @GetMapping("/categories")
    public List<ProductCategoryDto> getProductCategoriesList() {
        return productsService.findAllCategories().stream()
                .map(productCategoryConverter::entityToDto)
                .collect(Collectors.toList());
    }

}
