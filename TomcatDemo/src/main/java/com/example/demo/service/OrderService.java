package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.Entity.OrderEntity;

import java.util.List;

public interface OrderService extends IService<OrderEntity> {

    public String getMessage();

    public OrderEntity listOrderInfo();

    public String getOrderInfo();

    public List<String> OrderMessages();

}
