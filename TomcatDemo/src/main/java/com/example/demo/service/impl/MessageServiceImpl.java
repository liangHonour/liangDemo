package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Entity.MessageEntity;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.service.MessageService;

import java.util.List;

public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity> implements MessageService {
    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public MessageEntity listMessageInfo() {
        return null;
    }

    @Override
    public List<String> MessagesLists() {
        return null;
    }
}
