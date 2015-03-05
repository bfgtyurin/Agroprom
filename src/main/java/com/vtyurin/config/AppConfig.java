package com.vtyurin.config;


import com.vtyurin.controller.Controllers;
import com.vtyurin.dao.Dao;
import com.vtyurin.domain.Domains;
import com.vtyurin.service.Services;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {Domains.class, Dao.class, Services.class, Controllers.class})
public class AppConfig {

}
