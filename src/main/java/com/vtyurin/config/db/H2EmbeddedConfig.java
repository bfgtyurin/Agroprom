package com.vtyurin.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Profile(DatabaseConfigProfile.H2_MEM)
@PropertySource(value = {"classpath:persistence-h2.properties"})
public class H2EmbeddedConfig {

    private static final String PROPERTY_JDBC_DRIVER_CLASS = "jdbc.driverClassName";
    private static final String PROPERTY_JDBC_PASSWORD = "jdbc.password";
    private static final String PROPERTY_JDBC_URL = "jdbc.url";
    private static final String PROPERTY_JDBC_USER = "jdbc.username";
    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Inject
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(PROPERTY_JDBC_DRIVER_CLASS));
        dataSource.setUrl(env.getProperty(PROPERTY_JDBC_URL));
        dataSource.setUsername(env.getProperty(PROPERTY_JDBC_USER));
        dataSource.setPassword(env.getProperty(PROPERTY_JDBC_PASSWORD));

        return dataSource;
    }

    @Bean
    public Properties additionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty(PROPERTY_HIBERNATE_DIALECT, env.getProperty(PROPERTY_HIBERNATE_DIALECT));
        properties.setProperty(PROPERTY_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        properties.setProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO, env.getProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        properties.setProperty(PROPERTY_HIBERNATE_FORMAT_SQL, env.getProperty(PROPERTY_HIBERNATE_FORMAT_SQL));

        return properties;
    }
}
