package com.vtyurin.config;

import com.vtyurin.config.web.WebMvcConfig;
import com.vtyurin.service.ServiceMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = {ServiceMarker.class}, value = "com.vtyurin.config.db")
@Import({WebMvcConfig.class})
public class AppConfig {
}
