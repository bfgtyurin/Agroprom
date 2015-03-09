package com.vtyurin.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CategoryProductRelationship> categoryProductRelationship = new HashSet<>();

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

    public Set<CategoryProductRelationship> getCategoryProductRelationship() {
        return categoryProductRelationship;
    }

    public void setCategoryProductRelationship(Set<CategoryProductRelationship> categoryProductRelationship) {
        this.categoryProductRelationship = categoryProductRelationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(getCreated(), product.getCreated()) &&
                Objects.equals(getUpdated(), product.getUpdated()) &&
                Objects.equals(categoryProductRelationship, product.categoryProductRelationship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, getCreated(), getUpdated(), categoryProductRelationship);
    }
}
