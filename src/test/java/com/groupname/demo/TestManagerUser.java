package com.groupname.demo;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.ManagerUserService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestManagerUser {
    @Autowired
    ManagerUserService managerUserService;
    @Test
    public void testManagerUserGet(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        Result result;
        result = managerUserService.getNormalUserByCharacters(Consts.UserType.STUDENT.getValue(),userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
        result = managerUserService.getNormalUserByUserNoLike("000",userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
        result = managerUserService.getNormalUserByUserNoLike("",userEntity);
        System.out.println(result.isSuccess()+result.getMessage()+" "+result.getObject());
    }
    @Test
    public void testManagerUserUpdate(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        Result<ArrayList<UserEntity>> result;
        result=managerUserService.getNormalUserByCharacters(Consts.UserType.STUDENT.getValue(),userEntity);
        UserEntity user = result.getObject().get(0);
        user.setUserStatus(Consts.Status.DELETED.getValue());
        result=managerUserService.updateNormalUser(user,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
    }
    @Test
    public void contextLoads(){

    }
}
