package com.vtyurin.service;

import com.vtyurin.domain.Category;
import com.vtyurin.domain.CategoryBuilder;
import com.vtyurin.domain.Product;
import com.vtyurin.repository.CategoryRepository;
import com.vtyurin.service.internal.DefaultCategoryService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCategoryServiceTest {
    public static final String NAME = "Dummy Category";
    private static final Long ID = 1000L;
    private static final Category PARENT = null;
    private static final Set<Product> PRODUCTS = new HashSet<>();
    private static final DateTime TIME_NOW = DateTime.now();

    @Mock
    CategoryRepository repositoryMock;

    @InjectMocks
    DefaultCategoryService categoryService;

    @Test
    public void findById() throws Exception {
        Category model = new CategoryBuilder()
                .id(ID)
                .name(NAME)
                .parentId(PARENT)
                .products(PRODUCTS)
                .created(TIME_NOW)
                .updated(TIME_NOW)
                .build();
        when(repositoryMock.findOne(ID)).thenReturn(model);

        Category found = categoryService.findById(ID);
        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(model, found);
    }

    @Test
    public void findAll() {
        List<Category> expected = new ArrayList<>();
        when(repositoryMock.findAll()).thenReturn(expected);

        List<Category> found = categoryService.findAll();
        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(expected, found);
    }

    @Test
    public void findProductsById() {
        Category model = new CategoryBuilder()
                .id(ID)
                .name(NAME)
                .parentId(PARENT)
                .products(PRODUCTS)
                .created(TIME_NOW)
                .updated(TIME_NOW)
                .build();
        when(repositoryMock.findOne(ID)).thenReturn(model);

        Set<Product> products = categoryService.findProductsById(ID);
        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(PRODUCTS, products);
    }
}