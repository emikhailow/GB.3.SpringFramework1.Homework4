package com.geekbrains.homework4.dto;

import com.geekbrains.homework4.entities.Product;
import lombok.Data;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    private void recalculate(){
        totalPrice = 0;
        for (OrderItemDto item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void addProduct(Product product){
        if(addProduct(product.getId())){
            return;
        }
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public boolean addProduct(Long id) {
        for (OrderItemDto item : items) {
            if(item.getProductId().equals(id)){
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decreaseProduct(Long id){
        Iterator<OrderItemDto> iter = items.iterator();
        while(iter.hasNext())
        {
            OrderItemDto item = iter.next();
            if(item.getProductId().equals(id)){
                item.changeQuantity(-1);
                if(item.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void removeProduct(Long id){
        items.removeIf(i -> i.getProductId().equals(id));
        recalculate();
    }

    public void clear(){
        items.clear();
        recalculate();
    }
}
