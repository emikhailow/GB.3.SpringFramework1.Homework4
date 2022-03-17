package com.geekbrains.spring.web.core.converters;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.core.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductsConverter {

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }



}
