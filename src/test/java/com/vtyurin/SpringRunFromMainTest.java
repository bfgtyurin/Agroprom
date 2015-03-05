package com.vtyurin;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

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

        assertNotNull(ctx.getBean("emf"));
        assertNotNull(ctx.getBean("testDataSource"));
        assertNotNull(ctx.getBean("transactionManager"));
    }
}
