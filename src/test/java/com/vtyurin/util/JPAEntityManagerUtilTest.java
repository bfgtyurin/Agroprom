package com.vtyurin.util;

import com.vtyurin.TestPersistenceUnitName;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class JPAEntityManagerUtilTest implements TestPersistenceUnitName {

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void nonexistentPersistentUnitName() {
        JPAEntityManagerUtil.getEntityManager("nonexistent");
        fail("We shouldn't be able to acquire s EntityManager here");
    }

    @Test
    public void getEntityManager() {
        EntityManager entityManager = JPAEntityManagerUtil.getEntityManager(PERSISTENT_UNIT_NAME);
        assertNotNull(entityManager);
    }
}