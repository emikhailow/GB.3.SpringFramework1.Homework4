package com.geekbrains.homework4.repository;

import com.geekbrains.homework4.data.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsRepository {
    private final int INIT_PRODUCTS_COUNT = 10;
    private List<Product> productsList;

    @PostConstruct
    public void init(){
        productsList = new ArrayList<>();
        for (int i = 0; i < INIT_PRODUCTS_COUNT; i++) {
            productsList.add(new Product((long) i + 1));
        }
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public Product getByID(Long id){
        return productsList.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Item with id %011d not found", id)));
    }

    public void removeItem(Long id) {
        productsList.removeIf(x -> x.getId().equals(id));
    }

    public void addItem(String title) {
        productsList.add(new Product(getMaxID(), title));
    }

    private Long getMaxID() {

        if(productsList.isEmpty()){
            return 1L;
        }
        return productsList.stream()
                .reduce((a,b) -> a.getId() > b.getId() ? a : b)
                .get()
                .getId() + 1;
    }
}
