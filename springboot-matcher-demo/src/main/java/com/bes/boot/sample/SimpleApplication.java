package com.bes.boot.sample;

//import com.bes.boot.sample.extension.GracefulShutdownBes;
//import com.bes.enterprise.springboot.embedded.BesServletWebServerFactory;
/*import com.bes.boot.sample.extension.GracefulShutdownTomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;*/
import com.bes.boot.sample.servlet.MyServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@ServletComponentScan("com.bes.boot.sample.servlet")
public class SimpleApplication extends SpringBootServletInitializer {

    public static void main(String[] args)
    {
        SpringApplication.run(SimpleApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }



}
