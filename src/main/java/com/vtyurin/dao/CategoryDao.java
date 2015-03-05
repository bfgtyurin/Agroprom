package com.vtyurin.dao;

import com.vtyurin.domain.Category;

import java.util.List;

public interface CategoryDao {
    Category findOne(Long id);

    Category save(Category category);

    Category update(Category category);

    List<Category> findNestedCategoriesById(Long id);
}
