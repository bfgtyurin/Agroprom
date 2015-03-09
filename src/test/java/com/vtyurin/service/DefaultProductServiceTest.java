package com.vtyurin.service;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import com.vtyurin.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataConfigForTest.class})
public class DefaultProductServiceTest {

    @Inject
    ProductService productService;

    @Test
    public void findAllTest() {
        List<Product> productList = productService.findAll();
        assertTrue(productList.size() > 0);
    }
}
