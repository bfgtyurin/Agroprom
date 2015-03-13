package com.vtyurin;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vtyurin.config.db.CommonPersistenceConfig;
import com.vtyurin.config.db.DatabaseConfigProfile;
import com.vtyurin.config.db.H2EmbeddedConfig;
import com.vtyurin.config.db.HsqlEmbeddedConfig;
import com.vtyurin.domain.Category;
import com.vtyurin.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(DatabaseConfigProfile.H2_MEM)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ContextConfiguration(classes = {CommonPersistenceConfig.class})
public class CategoryRepositoryIT {
    private static final Long PARENT_ID = 1001L;
    private static final Long CHILD_ID = 1002L;
    private static final String PARENT_NAME = "Ветеринария";
    private static final String CHILD_NAME = "Антибактериальные препараты";

    @Inject
    CategoryRepository categoryRepository;

    @Test
    @Transactional
    @DatabaseSetup("category-entries.xml")
    public void findOne() {
        Category parent = categoryRepository.findOne(PARENT_ID);
        Category child = categoryRepository.findOne(CHILD_ID);

        assertEquals(parent.getName(), PARENT_NAME);
        assertEquals(child.getName(), CHILD_NAME);
        assertEquals(child.getParent().getName(), PARENT_NAME);
        assertTrue(parent.getNestedCategories().size() == 1);

        assertNull(parent.getParent());
        assertNotNull(child.getParent().getId());
    }
}
