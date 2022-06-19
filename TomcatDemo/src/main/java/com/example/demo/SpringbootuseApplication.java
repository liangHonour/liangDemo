package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//@Import({WebMvcConfiguration.class})
@MapperScan(basePackages = "com.example.demo.mapper")
@SpringBootApplication
//@ServletComponentScan
//@ComponentScan({"com.example.demo.controller", "com.example.demo.service"})
public class SpringbootuseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootuseApplication.class, args);
    }

}
