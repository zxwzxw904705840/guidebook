package com.groupname.demo;

import com.groupname.demo.consts.Consts;
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
        majorEntity.setMajorNo("66666");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("s12345");
        userEntity.setPassword("123456");
        userEntity.setUserName("我不想起名了");
        userEntity.setPhone("12345678906");
        userEntity.setCharacters(Consts.UserType.STUDENT.getValue());
        userEntity.setMajor(majorEntity);
        Result result = loginService.register(userEntity);
        System.out.println(result.isSuccess() + result.getMessage());
    }
    @Test
    public void contextLoads(){

    }
}
