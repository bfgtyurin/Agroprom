package com.vtyurin;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.db.DatabaseConfigProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles(DatabaseConfigProfile.H2_MEM)
@WebAppConfiguration
public class AppConfigTest {
    private static final Logger logger = LoggerFactory.getLogger(AppConfigTest.class);

    @Inject
    ApplicationContext context;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void springContext() {
        assertNotNull(context);
        assertNotNull(entityManager);
    }

    @Test
    public void listApplicationContextBeans() {
        String[] list = context.getBeanDefinitionNames();
        for (String bean : list) {
            logger.info("Bean '{}'", bean);
        }

    }
}
