package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.MessageEntity;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper extends BaseMapper<MessageEntity> {

     void insertMessage(@Param(value = "MessageEntity")MessageEntity message);
}
