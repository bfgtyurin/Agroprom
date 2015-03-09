package com.vtyurin.service.internal;

import com.vtyurin.domain.Category;
import com.vtyurin.domain.CategoryProductRelationship;
import com.vtyurin.domain.Product;
import com.vtyurin.repository.CategoryRepository;
import com.vtyurin.repository.ProductRepository;
import com.vtyurin.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DefaultCategoryService implements CategoryService {
    public static final Logger logger = LoggerFactory.getLogger(DefaultSellerService.class);

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    ProductRepository productRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
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
    public List<Product> findProductsById(Long id) {
        Category one = categoryRepository.findOne(id);
        List<Product> productList = new ArrayList<>();
        Set<CategoryProductRelationship> relationships = one.getCategoryProductRelationship();
        for (CategoryProductRelationship relationship : relationships) {
            Product product = relationship.getProduct();
            productList.add(product);
        }

        return productList;
    }

    @Override
    public List<Category> findNestedCategoriesById(Long id) {
        return null;
    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void delete(Long id) {

    }
}
