package com.geekbrains.spring.web.api.core;

public class ProductCategoryDto {
    private Long id;
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
