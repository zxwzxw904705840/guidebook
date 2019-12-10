package com.groupname.demo;

import com.groupname.demo.entity.MajorEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.LoginService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLogin {
    @Autowired
    LoginService loginService;
    @Test
    public void testRegister(){
        MajorEntity majorEntity = new MajorEntity();
        majorEntity.setMajorNo("666669");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("t10089");
        userEntity.setPassword("123456");
        userEntity.setUserName("我不想起名");
        userEntity.setPhone("12345678905");
        userEntity.setCharacters(1);
        userEntity.setMajor(majorEntity);
        Result result = loginService.register(userEntity);
        System.out.println(result.isSuccess() + result.getMessage());
    }
    @Test
    public void contextLoads(){

    }
}
