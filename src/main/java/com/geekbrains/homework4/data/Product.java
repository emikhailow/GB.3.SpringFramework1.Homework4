package com.geekbrains.homework4.data;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @Transient
    private final Random random = new Random();
    @Transient
    private static final int MIN_PRICE = 1;
    @Transient
    private static final int MAX_PRICE = 1000;

    public Product(Long id, String title) {
        this.id = id;
        this.title = title;
        generateRandomPrice();
    }

    public Product(String title) {
        this.title = title;
        generateRandomPrice();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public Product(Long id) {
        this.id = id;
        this.title = String.format("Item %011d", id);
        generateRandomPrice();
    }

    private void generateRandomPrice() {
        this.cost = random.nextInt(MAX_PRICE);
    }

    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }
}
