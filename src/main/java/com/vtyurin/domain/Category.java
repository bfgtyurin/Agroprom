package com.vtyurin.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
//@NamedQueries(
//        @NamedQuery(name = "Category.findByParentId", query = "SELECT c from Category c where c.parentId = :id"))
public class Category extends BaseEntity {

    private String name;
    private String description;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<CategoryProductRelationship> categoryProductRelationship = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this(name, 0);
    }

    public Category(String name, long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(description, category.description) &&
                Objects.equals(categoryProductRelationship, category.categoryProductRelationship) &&
                Objects.equals(parentId, category.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, parentId, categoryProductRelationship);
    }
}
