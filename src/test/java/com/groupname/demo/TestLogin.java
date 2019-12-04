package com.groupname.demo;

import com.groupname.demo.controller.LoginController;
import com.groupname.demo.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLogin {
    @Autowired
    LoginController loginController;

    @Test
    public void testLogin(){
        ArrayList<Map<String, Object>> data= new ArrayList<>();
        data.add(getParams("m10086","123456","2"));
        data.add(getParams("m10086","123457","2"));
        data.add(getParams("m10086","123456","1"));
        data.add(getParams("m11086","123456","2"));
        data.add(getParams("","123456","2"));
        data.add(getParams("m10086","","2"));
        data.add(getParams("m10086","123456",null));
        for(int i=0;i<data.size();i++){
            ResponseEntity responseEntity = loginController.login(data.get(i));

            System.out.println(i+":"+responseEntity.getBody());
        }

    }
    public Map<String, Object> getParams(String userNo,String password,String characters){
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("userNo",userNo);
        params.put("password",password);
        params.put("characters",characters);
        return params;
    }
    @Test
    public void testRegister(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m20086");
        userEntity.setUserName("路人乙");
        userEntity.setPassword("123456");
        userEntity.setPhone(null);
        userEntity.setEmail(null);
        userEntity.setCharacters(2);
        userEntity.setRemark(null);
        userEntity.setMajorNo(null);
        userEntity.setUserStatus(0);
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("user",userEntity);
        ResponseEntity responseEntity = loginController.register(params);
        System.out.println(responseEntity.getBody());
    }
    @Test
    public void contextLoads(){

    }

}
