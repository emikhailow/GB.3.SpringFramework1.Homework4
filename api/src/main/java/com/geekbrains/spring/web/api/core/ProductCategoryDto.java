package com.geekbrains.spring.web.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product category model")
public class ProductCategoryDto {
    @Schema(description = "Product category ID", required = true, example = "1")
    private Long id;
    @Schema(description = "Product category description", required = true, minLength = 3, maxLength = 255, example = "Category 1")
    private String title;

    public ProductCategoryDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public ProductCategoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
