package com.vtyurin.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@Profile(DatabaseConfigProfile.HSQL_EMBEDDED)
@PropertySource(value = {"classpath:persistence-hsqldb.properties"})
public class HsqlEmbeddedConfig {
    private static final Logger logger = LoggerFactory.getLogger(HsqlEmbeddedConfig.class);

    private static final String SQL_SCRIPT_PATH = "sql/test_database.sql";
    private static final String SQL_SCRIPT_ENCODING = "UTF-8";

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

    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource(SQL_SCRIPT_PATH));
        databasePopulator.setSqlScriptEncoding(SQL_SCRIPT_ENCODING);
        try {
            databasePopulator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            logger.error("Exception Populating Database", e);
        }

        return databasePopulator;
    }
}
