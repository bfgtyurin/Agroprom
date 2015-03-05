package com.vtyurin.dao;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import com.vtyurin.domain.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataConfigForTest.class})
public class JpaItemDaoTest {

    @Inject
    ItemDao dao;

    private Item testItem;

    @Before
    public void setUp() {

    }

    @Test
    public void testCreateTest() throws Exception {
        Item item = new Item("Spring", new BigDecimal("25000"));
        dao.save(item);
        assertNotNull(item.getId());
    }

    @Test
    @Transactional
    public void getByIdTest() {
        Item testItem = new Item("Notebook", new BigDecimal("125000"));
        dao.save(testItem);
        Long id = testItem.getId();
        assertNotNull(id);
        assertEquals(testItem, dao.getById(id));
    }
}