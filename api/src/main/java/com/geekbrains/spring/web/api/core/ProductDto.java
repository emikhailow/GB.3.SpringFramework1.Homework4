package com.geekbrains.spring.web.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Product model")
public class ProductDto {
    @Schema(description = "Product ID", required = true, example = "1")
    private Long id;
    @Schema(description = "Product title", required = true, minLength = 3, maxLength = 255, example = "Bread")
    private String title;
    @Schema(description = "Product price", required = true, example = "50")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductDto(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public ProductDto() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProductDto)) {
            return false;
        }

        ProductDto p = (ProductDto) obj;

        return id.equals(p.id) && title.equals(p.title);
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        if (title != null) {
            result = 31 * result + title.hashCode();
        }
        return result;
    }
}
