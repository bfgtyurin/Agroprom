package com.vtyurin.service;

import com.vtyurin.dao.CategoryDao;
import com.vtyurin.domain.Category;
import com.vtyurin.domain.CategoryProductRelationship;
import com.vtyurin.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class CategoryService {

    @Inject
    private CategoryDao categoryDao;

    public Category findOne(Long id) {
        return categoryDao.findOne(id);
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public Category update(Category category) {
        return categoryDao.update(category);
    }

    public Set<Product> getItemsById(Long id) {
        Category category = categoryDao.findOne(id);
        Set<CategoryProductRelationship> relationships = category.getCategoryProductRelationship();
        Set<Product> products = new HashSet<>();
        for (CategoryProductRelationship relationship : relationships) {
            products.add(relationship.getProduct());
        }

        return products;
    }

    public List<Category> findNestedCategoriesById(Long id) {
        return categoryDao.findNestedCategoriesById(id);
    }
}
