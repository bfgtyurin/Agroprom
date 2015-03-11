package com.vtyurin.config;

import com.vtyurin.config.web.WebMvcConfig;
import com.vtyurin.repository.RepositoryMarker;
import com.vtyurin.service.ServiceMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {ServiceMarker.class}, value = "com.vtyurin.config.db")
@EnableJpaRepositories(basePackageClasses = RepositoryMarker.class)
@Import({WebMvcConfig.class})
public class AppConfig {
}
