package com.vtyurin.config.web;


import com.vtyurin.config.AppConfig;
import com.vtyurin.config.db.DatabaseConfigProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    private final static Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("Trying load WebAppInitializer");
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        rootContext.getEnvironment().addActiveProfile(DatabaseConfigProfile.HSQL_EMBEDDED);

        configureDispatcherServlet(servletContext, rootContext);
        configureCharacterEncodingFilter(servletContext);

        servletContext.addListener(new ContextLoaderListener(rootContext));
        logger.info("WebAppInitializer was load");
    }

    private void configureDispatcherServlet(ServletContext servletContext, AnnotationConfigWebApplicationContext rootContext) {
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private void configureCharacterEncodingFilter(ServletContext servletContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForServletNames(null, true, "/*");
    }
}
