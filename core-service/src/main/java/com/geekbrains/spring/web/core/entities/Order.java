package com.geekbrains.spring.web.core.entities;

import com.geekbrains.spring.web.core.data.OrderStatuses;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "admin_area_1")
    private String adminArea1;

    @Column(name = "admin_area_2")
    private String adminArea2;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderItem> items;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(Builder builder) {
        this.username = builder.username;
        this.status = builder.status;
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.adminArea1 = builder.adminArea1;
        this.adminArea2 = builder.adminArea2;
        this.postalCode = builder.postalCode;
        this.countryCode = builder.countryCode;
        this.phone = builder.phone;
    }

    public static class Builder {
        private BigDecimal totalPrice;
        private String address;
        private String addressLine1;
        private String addressLine2;
        private String adminArea1;
        private String adminArea2;
        private String postalCode;
        private String countryCode;
        private String phone;
        private String username;
        private String status;

        public Builder(String username) {
            this.username = username;
            this.status = OrderStatuses.CREATED.name();
        }

        public Builder totalPrice(BigDecimal totalPrice){
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder contactInfo(String address, String phone){
            this.address = address;
            this.phone = phone;
            return this;
        }

        public Builder payPalAddressInfo(String addressLine1, String addressLine2, String adminArea1, String adminArea2, String postalCode, String countryCode){
            this.addressLine1 = addressLine1;
            this.addressLine2 = addressLine2;
            this.adminArea1 = adminArea1;
            this.adminArea2 = adminArea2;
            this.postalCode = postalCode;
            this.countryCode = countryCode;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

    }

    public void addItem(OrderItem orderItem){
        this.items.add(orderItem);
    }

}
