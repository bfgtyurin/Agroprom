package com.vtyurin.config;

import com.vtyurin.controller.ControllerMarker;
import com.vtyurin.dao.DaoMarker;
import com.vtyurin.service.ServiceMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        DaoMarker.class,
        ServiceMarker.class,
        ControllerMarker.class})
public class AppConfig {
}
