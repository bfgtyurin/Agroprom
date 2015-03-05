package com.vtyurin.util;

import com.vtyurin.domain.Category;
import com.vtyurin.domain.CategoryItemRelationship;
import com.vtyurin.domain.Item;
import com.vtyurin.domain.Seller;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    public static SessionFactory buildHibernateAnnotationConfigSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(CategoryItemRelationship.class);
        configuration.addAnnotatedClass(Seller.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        configuration.setProperty("javax.persistence.jdbc.url", "org.hsqldb.jdbcDriver");
        configuration.setProperty("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost:9001");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.setProperty("show_sql", "true");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
