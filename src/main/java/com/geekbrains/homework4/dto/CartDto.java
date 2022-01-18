package com.geekbrains.homework4.dto;

import com.geekbrains.homework4.data.Cart;
import com.geekbrains.homework4.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    List<ProductDto> productDtoList;

    public CartDto(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public CartDto(Cart cart){
        this.productDtoList = new ArrayList<>();
        for (Product product : cart.getProductList()) {
            this.productDtoList.add(new ProductDto(product));
        }
    }

    public CartDto() {
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
