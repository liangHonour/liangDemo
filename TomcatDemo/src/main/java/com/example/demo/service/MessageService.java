package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.Entity.MessageEntity;

import java.util.List;

public interface MessageService  extends IService<MessageEntity> {

    public String getMessage();

    public MessageEntity listMessageInfo();

    public List<String> MessagesLists();

}
