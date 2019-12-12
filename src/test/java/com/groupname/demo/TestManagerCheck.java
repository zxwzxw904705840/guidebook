package com.groupname.demo;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.MessageEntity;
import com.groupname.demo.entity.ReviewEntity;
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
        ReviewEntity reviewEntity=new ReviewEntity();
        reviewEntity.setReviewType(Consts.ReviewType.MESSAGE_NO.getValue());
        reviewEntity.setReviewObjectNo("13048e40a2cf5353ab5ed26a762c01bc");
        Result result;
        result = managerCheckService.review(reviewEntity,true,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        reviewEntity.setReviewObjectNo("3251888dedda967d47c640a0f9f3f48b");
        result = managerCheckService.review(reviewEntity,false,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        reviewEntity.setReviewType(Consts.ReviewType.PURCHASE_NO.getValue());
        reviewEntity.setReviewObjectNo("934a1889ec28314967929677caf8e24c");
        result = managerCheckService.review(reviewEntity,true,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        reviewEntity.setReviewType(Consts.ReviewType.USER_NO.getValue());
        reviewEntity.setReviewObjectNo("t10086");
        result = managerCheckService.review(reviewEntity,true,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
    }
    @Test
    public void contextLoads(){

    }
}
