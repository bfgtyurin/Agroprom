package com.vtyurin.config;

import com.vtyurin.config.db.CommonPersistenceConfig;
import com.vtyurin.config.web.WebMvcConfig;
import com.vtyurin.service.ServiceMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = {ServiceMarker.class})
@Import({WebMvcConfig.class, CommonPersistenceConfig.class})
public class AppConfig {
}
