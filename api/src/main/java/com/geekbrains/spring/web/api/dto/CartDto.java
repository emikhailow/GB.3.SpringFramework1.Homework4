package com.geekbrains.spring.web.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class CartDto {

    private List<OrderItemDto> items;
    private int totalPrice;

    public CartDto() {
        this.items = new ArrayList<>();
    }

    private void recalculate(){
        totalPrice = 0;
        for (OrderItemDto item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void add(ProductDto productDto){
        if(add(productDto.getId())){
            return;
        }
        items.add(new OrderItemDto(productDto));
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
    public void merge(CartDto another) {
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
