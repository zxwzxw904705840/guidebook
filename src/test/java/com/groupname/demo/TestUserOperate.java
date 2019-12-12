package com.groupname.demo;

import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.UserOperateService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserOperate {
    @Autowired
    UserOperateService userOperateService;
    @Test
    public void TestUserOperateUpdate(){
        Result result;
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("t10010");
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassNo("d83c02e8d10fe26da8040d6986c1d71e");
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("9787040446081");
        classEntity.setGuidebook(bookEntity);
        result = userOperateService.updateClassGuidebook(classEntity,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
    }

    @Test
    public void contextLoads(){

    }
}
