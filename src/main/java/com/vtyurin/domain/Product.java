package com.vtyurin.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Product() {
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(getCreated(), product.getCreated()) &&
                Objects.equals(getUpdated(), product.getUpdated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, getCreated(), getUpdated());
    }
}
