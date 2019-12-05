package com.groupname.demo.controller;

import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.LoginService;
import com.groupname.demo.utils.Result;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Result> doLogin(HttpServletRequest request){
        System.out.println("login");
        String userNo = request.getParameter("userNo");
        String password = request.getParameter("password");
        Result<UserEntity> result = loginService.Login(userNo,password);
        if(!result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        HttpSession session=request.getSession(true);
        session.setAttribute("userNo",result.getObject().getUserNo());
        session.setAttribute("password",result.getObject().getPassword());
        //System.out.println(request.getParameter("password"));
        /*String userNo = params.get("userNo").toString();
        String password = params.get("password").toString();
        Result result = loginService.Login(userNo,password);*/
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest request){
        System.out.println("register");
        return "register";
    }

    @RequestMapping(value = "doRegister",method = RequestMethod.POST)
    public ResponseEntity<Result> doRegister(@RequestBody Map<String,Object> params){
        UserEntity user = JSON.parseObject(JSON.toJSONString(params.get("user")),UserEntity.class);
        Result result = loginService.register(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
