package com.groupname.demo;

import com.groupname.demo.entity.MessageEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.ManagerCheckService;
import com.groupname.demo.service.MessageService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestManagerCheck {
    @Autowired
    ManagerCheckService managerCheckService;
    @Test
    public void testManagerCheckGet(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        Result result;
        result=managerCheckService.getUserReviewList(userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        System.out.println(result.getObject());
        result=managerCheckService.getMessageReviewList(userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        System.out.println(result.getObject());
        result=managerCheckService.getPurchaseReviewList(userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        System.out.println(result.getObject());
        result=managerCheckService.getGuidebookChangeReviewList(userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        System.out.println(result.getObject());
    }
    @Test
    public void testManagerCheckUpdateMessage(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        Result result;
        result = managerCheckService.reviewMessage("13048e40a2cf5353ab5ed26a762c01bc",true,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        result = managerCheckService.reviewMessage("3251888dedda967d47c640a0f9f3f48b",false,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
    }
    @Test
    public void contextLoads(){

    }
}
