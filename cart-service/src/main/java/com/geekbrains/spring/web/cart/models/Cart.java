package com.geekbrains.spring.web.cart.models;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
public class Cart {

    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    private void recalculate(){
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void add(ProductDto productDto){
        if(add(productDto.getId())){
            return;
        }
        items.add(new CartItem(productDto));
        recalculate();
    }

    public boolean add(Long id) {
        for (CartItem item : items) {
            if(item.getProductId().equals(id)){
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decrement(Long productId){
        Iterator<CartItem> iter = items.iterator();
        while(iter.hasNext())
        {
            CartItem item = iter.next();
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
        for (CartItem anotherItem : another.items) {
            boolean merged = false;
            for (CartItem myItem : items) {
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
