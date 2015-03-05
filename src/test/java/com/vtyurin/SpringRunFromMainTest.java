package com.vtyurin;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import com.vtyurin.dao.ItemDao;
import com.vtyurin.domain.Item;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;

@Configuration
public class SpringRunFromMainTest {
    public static final Logger log = LoggerFactory.getLogger(SpringRunFromMainTest.class);

    @Test
    public void runContextTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.register(DataConfigForTest.class);
        ctx.refresh();

        ItemDao dao = ctx.getBean(ItemDao.class);
        Item item = new Item("Spring Context", new BigDecimal("10.00"));
        log.info("Trying to save new Item");
        log.info("Timestamp created" + item.getCreated());
        log.info("Timestamp updated" + item.getUpdated());
        dao.save(item);
        assertNotNull(item.getId());
        log.info("Done");
    }

}
