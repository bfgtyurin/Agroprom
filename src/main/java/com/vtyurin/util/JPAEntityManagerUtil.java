package com.vtyurin.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityManagerUtil {

    public static EntityManager getEntityManager(String persistenceUnitName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        return entityManagerFactory.createEntityManager();
    }
}
