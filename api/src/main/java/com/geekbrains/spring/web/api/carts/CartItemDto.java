package com.geekbrains.spring.web.api.carts;

import com.geekbrains.spring.web.api.core.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Cart item model")
public class CartItemDto {
    @Schema(description = "Product ID", required = true, example = "1")
    private Long productId;
    @Schema(description = "Product title", required = true, example = "Bread")
    private String productTitle;
    @Schema(description = "Quantity", required = true, example = "10")
    private int quantity;
    @Schema(description = "Price for product", required = true, example = "12.34")
    private BigDecimal pricePerProduct;
    @Schema(description = "Total price", required = true, example = "123.40")
    private BigDecimal price;

    public CartItemDto(Long productId, String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public CartItemDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
