package com.geekbrains.spring.web.core.converters;

import com.geekbrains.spring.web.core.dto.ProductCategoryDto;
import com.geekbrains.spring.web.core.entities.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCategoryConverter {

    public ProductCategoryDto entityToDto(ProductCategory productCategory){
        return new ProductCategoryDto(productCategory.getId(), productCategory.getTitle());
    }
}
