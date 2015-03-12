package com.vtyurin.domain;

import org.joda.time.DateTime;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Set;

public class CategoryBuilder {

    private Long id;
    private String name;
    private Category parent;
    private Set<Category> nestedCategories;
    private Set<Product> products;
    private DateTime created;
    private DateTime updated;

    public CategoryBuilder() {
    }

    public CategoryBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder parentId(Category parent) {
        this.parent = parent;
        return this;
    }

    public CategoryBuilder products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public CategoryBuilder created(DateTime created) {
        this.created = created;
        return this;
    }

    public CategoryBuilder updated(DateTime updated) {
        this.updated = updated;
        return this;
    }

    public Category build() {
        Category category = Category.getBuilder()
                .name(name)
                .parent(parent)
                .nestedCategories(nestedCategories)
                .products(products)
                .build();

        ReflectionTestUtils.setField(category, "id", id);
        ReflectionTestUtils.setField(category, "created", created);
        ReflectionTestUtils.setField(category, "updated", updated);

        return category;
    }
}
