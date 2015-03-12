package com.vtyurin.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category extends BaseEntity {

    private String name;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Category> nestedCategories;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;

    protected Category() {
    }

    private Category(Builder builder) {
        this.name = builder.name;
        this.parent = builder.parent;
        this.nestedCategories = builder.nestedCategories;
        this.products = builder.products;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Category getParent() {
        return parent;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set<Category> getNestedCategories() {
        return nestedCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(parent, category.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }

    static class Builder {
        private String name;
        private Category parent;
        private Set<Category> nestedCategories;
        private Set<Product> products;

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder parent(Category parentId) {
            this.parent = parentId;
            return this;
        }

        Builder nestedCategories(Set<Category> nestedCategories) {
            this.nestedCategories = nestedCategories;
            return this;
        }

        Builder products(Set<Product> products) {
            this.products = products;
            return this;
        }

        Category build() {
            return new Category(this);
        }
    }
}
