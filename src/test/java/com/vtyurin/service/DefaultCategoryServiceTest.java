package com.vtyurin.service;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import com.vtyurin.domain.Category;
import com.vtyurin.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataConfigForTest.class})
public class DefaultCategoryServiceTest {

    @Inject
    CategoryService service;

    @Test
    public void findById() throws Exception {
        Long id = 1001L;
        service.findById(id);
        assertNotNull(id);
    }

    @Test
    public void testSaveOneTopLevel() throws Exception {
        Category category = new Category("Dummy Top Category");
        service.create(category);
        assertNotNull(category.getId());
    }

    @Test
    public void testSaveOneWithParent() throws Exception {
        Category parent = service.findById(1001L);
        Category category = new Category("Dummy Child Category", parent.getId());
        service.create(category);
        assertNotNull(category.getId());
        assertEquals(service.findById(category.getId()).getParentId(), parent.getId());
    }

    @Test
    public void testUpdate() {
        Long id = 1002L;
        Category category = service.findById(id);
        String oldName = category.getName();
        LocalDateTime oldCreated = category.getCreated();
        LocalDateTime oldUpdated = category.getUpdated();
        category.setName("Dummy From Test");
        service.update(category);

        category = service.findById(id);
        assertNotEquals(oldName, category.getName());
        assertEquals(oldCreated, category.getCreated());
        assertNotEquals(oldUpdated, category.getUpdated());
    }

    @Test
    public void testGetItemsById() {
        List<Product> itemsById = service.findProductsById((long) 1002);
        int sizeOfDummyItemsInTestCategory = 5;
        assertEquals(sizeOfDummyItemsInTestCategory, itemsById.size());
    }

    @Test
    public void testFindNestedCategoriesById() throws Exception {
        Long topLevelCategory = 0L;
        List<Category> topLevelCategories = service.findNestedCategoriesById(topLevelCategory);
        for (Category category : topLevelCategories) {
            assertEquals(topLevelCategory, category.getParentId());
        }

        Long nestedCategoryId = 1001L;
        List<Category> nestedCategories = service.findNestedCategoriesById(nestedCategoryId);
        for (Category category : nestedCategories) {
            Long id = category.getParentId();
            assertEquals(nestedCategoryId, id);
        }
    }
}