package com.vtyurin.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY_ITEM_RELATIONSHIP")
public class CategoryItemRelationship {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn (name = "ITEM_ID", insertable = false, updatable = false)
    private Item item;

    private Timestamp dateAdded;

    public CategoryItemRelationship() {
    }

    public static class Id implements Serializable {

        @Column(name = "CATEGORY_ID")
        private Long categoryId;

        @Column(name = "ITEM_ID")
        private Long itemId;

        public Id() {}

        public Id(Long categoryId, Long itemId) {
            this.categoryId = categoryId;
            this.itemId = itemId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(categoryId, id.categoryId) &&
                    Objects.equals(itemId, id.itemId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(categoryId, itemId);
        }
    }

    public CategoryItemRelationship(Category category, Item item, Timestamp dateAdded) {
        this.item = item;
        this.category = category;
        this.getId().categoryId = category.getId();
        this.getId().itemId = item.getId();
        // lol
        category.getCategoryItemRelationship().add(this);
        item.getCategoryItemRelationship().add(this);
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
