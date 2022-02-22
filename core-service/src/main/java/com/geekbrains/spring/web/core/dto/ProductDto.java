package com.geekbrains.spring.web.core.dto;

import com.geekbrains.spring.web.core.entities.Product;

public class ProductDto {
    private Long id;
    private String title;
    private int price;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.price = p.getPrice();
        this.title = p.getTitle();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductDto(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public ProductDto() {
    }

}
