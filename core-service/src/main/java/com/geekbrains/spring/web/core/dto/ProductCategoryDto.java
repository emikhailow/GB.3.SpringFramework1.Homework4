package com.geekbrains.spring.web.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCategoryDto {
    private Long id;
    private String title;

}
