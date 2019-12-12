package com.groupname.demo;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.MessageEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.ManagerOperateService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestManagerOperate {
    @Autowired
    ManagerOperateService managerOperateService;

    @Test
    public void testManagerOperateGet(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        Result result;
        result = managerOperateService.getCourseByTerm(4,userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
        result = managerOperateService.getAllCourse(userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
        result = managerOperateService.getCourseByCourseName("学",userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
        result = managerOperateService.getCourseByCourseName("物理",userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
        result = managerOperateService.getMessage(userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
    }
    @Test
    public void testManagerOperateUpdate(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        Result result;
        result = managerOperateService.deleteMessage("d5a4792aeb3785424d033a58ba2f87c8",userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("9787040446081");
        bookEntity.setPublishingHouse("高等教育出版社");
        bookEntity.setPublishingData(2019);
        bookEntity.setNumber(2147483647);
        bookEntity.setBookName("大学物理");
        bookEntity.setAuthor("宋更新2");
        bookEntity.setAuthorMajor(Consts.BookMajor.PHYSICS.getValue());
        result=managerOperateService.updateBook(bookEntity,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
    }

    @Test
    public void contextLoads(){

    }
}
