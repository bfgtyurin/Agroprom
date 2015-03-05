package com.vtyurin.service;

import com.vtyurin.dao.CategoryDao;
import com.vtyurin.domain.Category;
import com.vtyurin.domain.CategoryItemRelationship;
import com.vtyurin.domain.Item;
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

    public Set<Item> getItemsById(Long id) {
        Category category = categoryDao.findOne(id);
        Set<CategoryItemRelationship> relationships = category.getCategoryItemRelationship();
        Set<Item> items = new HashSet<>();
        for (CategoryItemRelationship relationship : relationships) {
            items.add(relationship.getItem());
        }

        return items;
    }

    public List<Category> findNestedCategoriesById(Long id) {
        return categoryDao.findNestedCategoriesById(id);
    }
}
