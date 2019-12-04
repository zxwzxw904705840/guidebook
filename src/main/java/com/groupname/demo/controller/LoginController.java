package com.groupname.demo.controller;

import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.LoginService;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResponseEntity<Result> login(@RequestBody Map<String,Object> params){
        String userNo = params.get("userNo").toString();
        String password = params.get("password").toString();
        Result result = loginService.Login(userNo,password);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseEntity<Result> register(@RequestBody Map<String,Object> params){
        UserEntity userEntity = JSON.parseObject(JSON.toJSONString(params.get("user")),UserEntity.class);
        Result result = loginService.register(userEntity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
