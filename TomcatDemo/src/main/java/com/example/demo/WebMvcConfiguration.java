package com.example.demo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;


@Configuration
@ComponentScan
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    private static final Logger logger = LoggerFactory.getLogger(com.example.demo.WebMvcConfiguration.class);

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean((Servlet)dispatcherServlet, new String[0]);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return registration;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[] { "/templates/**" }).addResourceLocations(new String[] { "classpath:/templates/" });
        super.addResourceHandlers(registry);
    }

    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
}
