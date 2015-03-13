package com.vtyurin;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.db.DatabaseConfigProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;

public class SpringRunFromMainTest {
    public static final Logger log = LoggerFactory.getLogger(SpringRunFromMainTest.class);

    public void runContextTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.getEnvironment().addActiveProfile(DatabaseConfigProfile.HSQL_EMBEDDED);
        ctx.refresh();

        assertNotNull(ctx.getBean("entityManagerFactory"));
        assertNotNull(ctx.getBean("testDataSource"));
        assertNotNull(ctx.getBean("transactionManager"));
    }
}
