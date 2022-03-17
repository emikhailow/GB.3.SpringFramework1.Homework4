package com.geekbrains.spring.web.recommends.models;

import com.geekbrains.spring.web.api.core.ProductDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Recommends {
    private List<ProductDto> mostOrderedItems;
    private List<ProductDto> mostAddedToCartItems;

    @PostConstruct
    public void init(){

        this.mostOrderedItems = new ArrayList<>();
        this.mostAddedToCartItems = new ArrayList<>();
    }

    public List<ProductDto> getMostOrderedItems(Integer count) {
        return mostOrderedItems.stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    public void setMostOrderedItems(List<ProductDto> mostOrderedItems) {
        this.mostOrderedItems = mostOrderedItems;
    }

    public List<ProductDto> getMostAddedToCartItems(Integer count) {
        return mostAddedToCartItems.stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    public void setMostAddedToCartItems(List<ProductDto> mostAddedToCartItems) {
        this.mostAddedToCartItems = mostAddedToCartItems;
    }
}
