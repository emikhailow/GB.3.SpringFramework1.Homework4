package com.geekbrains.homework4.dto;

import com.geekbrains.homework4.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCategoryDto {
    private Long id;
    private String title;

}
