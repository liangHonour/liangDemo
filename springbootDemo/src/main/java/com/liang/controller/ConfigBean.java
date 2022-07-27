package com.liang.controller;

import com.liang.controller.bean.BeanConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class ConfigBean {
    @Bean
    public BeanConfig setBeanConfig(){
        return new BeanConfig();
    }
}
