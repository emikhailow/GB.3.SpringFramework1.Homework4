package com.geekbrains.homework4.dto;

import com.geekbrains.homework4.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

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

    public void add(Product product){
        if(add(product.getId())){
            return;
        }
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public boolean add(Long id) {
        for (OrderItemDto item : items) {
            if(item.getProductId().equals(id)){
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decrement(Long productId){
        Iterator<OrderItemDto> iter = items.iterator();
        while(iter.hasNext())
        {
            OrderItemDto item = iter.next();
            if(item.getProductId().equals(productId)){
                item.changeQuantity(-1);
                if(item.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId){
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
    }

    public void clear(){
        items.clear();
        recalculate();
    }
    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }

}
