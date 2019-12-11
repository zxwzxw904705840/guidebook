package com.groupname.demo;

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
    public void contextLoads(){

    }
}
