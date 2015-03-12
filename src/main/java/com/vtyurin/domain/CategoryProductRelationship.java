package com.vtyurin.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY_PRODUCT_RELATIONSHIP")
public class CategoryProductRelationship {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
    private Product product;

    private Timestamp dateAdded;

    public CategoryProductRelationship() {
    }

    public static class Id implements Serializable {

        @Column(name = "CATEGORY_ID")
        private Long categoryId;

        @Column(name = "PRODUCT_ID")
        private Long productId;

        public Id() {
        }

        public Id(Long categoryId, Long productId) {
            this.categoryId = categoryId;
            this.productId = productId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(categoryId, id.categoryId) &&
                    Objects.equals(productId, id.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(categoryId, productId);
        }
    }
//
//    public CategoryProductRelationship(Category category, Product product, Timestamp dateAdded) {
//        this.product = product;
//        this.category = category;
//        this.getId().categoryId = category.getId();
//        this.getId().productId = product.getId();
//        // lol
//        category.getCategoryProductRelationship().add(this);
//        product.getCategoryProductRelationship().add(this);
//    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
