package com.example.demo.controller;

import com.example.demo.Entity.OrderEntity;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping({"/"})
    @ResponseBody
    public String hello() {
        return this.orderService.getMessage();
    }

    public String getMessage() {
        return null;
    }

    @GetMapping({"/getInsertOrderInfo"})
    @ResponseBody
    public String getInsertOrderInfo() {
        return this.orderService.getOrderInfo();
    }

    @GetMapping({"/getOrderInfo"})
    @ResponseBody
    public String getOrderInfo() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity = this.orderService.listOrderInfo();
        return orderEntity.getName() + orderEntity.getPassword();
    }

}
