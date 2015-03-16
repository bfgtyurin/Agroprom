package com.vtyurin.service.internal;

import com.vtyurin.domain.Category;
import com.vtyurin.domain.Product;
import com.vtyurin.repository.CategoryRepository;
import com.vtyurin.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class DefaultCategoryService implements CategoryService {
    public static final Logger logger = LoggerFactory.getLogger(DefaultSellerService.class);

    @Inject
    CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Set<Product> findProductsById(Long id) {
        Category category = categoryRepository.findOne(id);
        return category.getProducts();
    }

    @Override
    public List<Category> findNestedCategoriesById(Long id) {
        return categoryRepository.findByParentId(id);
    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Map<Long, String> findAllSimple() {
        List<Category> list = categoryRepository.findAll();
        Map<Long, String> result = new HashMap<>();
        for (Category category : list) {
            result.put(category.getId(), category.getName());
        }

        return result;
    }
}
