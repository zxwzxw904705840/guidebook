package com.groupname.demo;

import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.MessageEntity;
import com.groupname.demo.entity.PurchaseEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.MessageRepository;
import com.groupname.demo.service.MessageService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMessage {
    @Autowired
    MessageService messageService;
    @Test
    public void testAddMessage(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("t10011");
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setUser(userEntity);
        messageEntity.setMessageContent("留言测试");
        Result result=messageService.addMessage(messageEntity);
        System.out.println(result.isSuccess()+result.getMessage());
    }
    @Test
    public void contextLoads(){

    }
}
