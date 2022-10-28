package com.liang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/hello")
    public String hello(){
        //由于restTemplate.getForEntity返回的不是String格式，所以后面加上getBody()
        return restTemplate.getForEntity("http://providers/hello",String.class).getBody();
    }
}

