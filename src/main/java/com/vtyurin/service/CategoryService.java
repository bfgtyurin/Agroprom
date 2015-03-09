package com.vtyurin.service;

import com.vtyurin.domain.Category;
import com.vtyurin.domain.Product;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    Category update(Category category);

    Category findById(Long id);

    List<Category> findAll();

    List<Product> findProductsById(Long id);

    List<Category> findNestedCategoriesById(Long id);

    void delete(Category category);

    void delete(Long id);

}
