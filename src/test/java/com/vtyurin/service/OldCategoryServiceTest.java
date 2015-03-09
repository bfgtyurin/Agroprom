package com.vtyurin.service;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import com.vtyurin.domain.Category;
import com.vtyurin.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataConfigForTest.class})
public class OldCategoryServiceTest {

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
        Timestamp oldCreated = category.getCreated();
        Timestamp oldUpdated = category.getUpdated();
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

    @Ignore
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