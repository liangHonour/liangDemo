package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Entity.OrderEntity;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;

import java.util.List;

@Service
public class OrderServiceImpl  extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ServletContext servletContext;

    @Value("${name:hello pig!}")
    private String name;
    @Override
    public String getMessage() {
        //String name = (String) this.servletContext.getAttribute("abc");
        return "Welcome to " + this.name + "!";
    }

    @Override
    public OrderEntity listOrderInfo() {
        return orderMapper.selectById(1);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public String getOrderInfo() {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setName("xiaowang");
        orderEntity.setPassword("123456");
        orderEntity.setOrderInfo("已购买");
        System.out.println(123);
        try {
            orderMapper.insertUser(orderEntity);
        } catch (Exception e) {
            System.out.println("添加失败");
            throw  e;
        }
        return "success";
    }


    @Override
    public List<String> OrderMessages() {
        return null;
    }
}
