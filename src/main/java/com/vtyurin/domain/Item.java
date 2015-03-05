package com.vtyurin.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Item extends BaseEntity {

    private String name;
    private BigDecimal price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<CategoryItemRelationship> categoryItemRelationship = new HashSet<>();

    public Item() {
    }

    public Item(String name, BigDecimal price) {
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

    public Set<CategoryItemRelationship> getCategoryItemRelationship() {
        return categoryItemRelationship;
    }

    public void setCategoryItemRelationship(Set<CategoryItemRelationship> categoryItemRelationship) {
        this.categoryItemRelationship = categoryItemRelationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(getCreated(), item.getCreated()) &&
                Objects.equals(getUpdated(), item.getUpdated()) &&
                Objects.equals(categoryItemRelationship, item.categoryItemRelationship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, getCreated(), getUpdated() , categoryItemRelationship);
    }
}
