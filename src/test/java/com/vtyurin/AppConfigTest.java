package com.vtyurin;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataConfigForTest.class})
public class AppConfigTest {

    @Inject
    ApplicationContext context;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void springContext() {
        assertNotNull(context);
        assertNotNull(entityManager);
    }

}
