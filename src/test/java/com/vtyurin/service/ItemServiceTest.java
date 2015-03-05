package com.vtyurin.service;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import com.vtyurin.domain.Category;
import com.vtyurin.domain.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataConfigForTest.class})
public class ItemServiceTest {
    public static final Logger logger = LoggerFactory.getLogger(ItemServiceTest.class);

    @Inject
    private ItemService itemService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {
        Item item = itemService.findOne((long) 2000);
        assertNotNull(item);
        Item toDB = new Item("Самолет", new BigDecimal("55").setScale(2));
        itemService.save(toDB);
        logger.info("new Bigdecimal: " + toDB.getPrice());
        logger.info("new Bigdecimal: " + itemService.findOne(toDB.getId()).getPrice());
        assertEquals(toDB.getPrice(), itemService.findOne(toDB.getId()).getPrice());
    }

    @Test
    public void testGetCategoriesById() throws Exception {
        Set<Category> categories = itemService.getCategoriesById((long) 2000);
        assertTrue(categories.size() == 1);
    }
}