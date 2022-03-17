package com.geekbrains.spring.web.api.core;

public class ProductDto {
    private Long id;
    private String title;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductDto(Long id, String title, int price) {
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
