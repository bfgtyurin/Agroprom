package com.vtyurin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:persistence-hsqldb.properties", "classpath:db-defaults.properties"})
public class DataConfigForTest {
    private static final Logger logger = LoggerFactory.getLogger(DataConfigForTest.class);

    @Inject
    private Environment env;

    @Bean
    public Long getCategoryDefaultTopLevelId() {
        return 0L;
    }

    @Bean()
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setJpaProperties(additionalProperties());
        factory.setPackagesToScan("com.vtyurin.domain");

        return factory;
    }

    @Bean(name = "testDataSource")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        return dataSource;
    }

    private Properties additionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        return properties;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("sql/test_database.sql"));
        databasePopulator.setSqlScriptEncoding("UTF-8");
        try {
            databasePopulator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            logger.error("Exception Populating Database", e);
        }

        return databasePopulator;
    }
}
