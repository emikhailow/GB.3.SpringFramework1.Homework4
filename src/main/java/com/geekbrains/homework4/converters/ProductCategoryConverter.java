package com.geekbrains.homework4.converters;

import com.geekbrains.homework4.dto.OrderDto;
import com.geekbrains.homework4.dto.ProductCategoryDto;
import com.geekbrains.homework4.entities.Order;
import com.geekbrains.homework4.entities.Product;
import com.geekbrains.homework4.entities.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductCategoryConverter {

    public ProductCategoryDto entityToDto(ProductCategory productCategory){
        return new ProductCategoryDto(productCategory.getId(), productCategory.getTitle());
    }
}
