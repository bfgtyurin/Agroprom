package com.vtyurin.mvc;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.db.DbConfigProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ActiveProfiles(DbConfigProfile.HSQL_EMBEDDED)
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Inject
    WebApplicationContext webContext;

    @Before
    public void setUp() {
        MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void test() {

    }

}
