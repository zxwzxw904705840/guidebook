package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Struct;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public Result<Object> Login(String userNo, String password, Integer characters){
        if(userNo==null||userNo.equals("")){
            return new Result<>(false,Consts.USERNO_IS_EMPTY,null);
        }
        if(password==null||password.equals("")){
            return new Result<>(false,Consts.PASSWORD_IS_EMPTY,null);
        }
        if(userNo.length()>10){
            return new Result<>(false, Consts.WRONG_USERNO_OR_PASSWORD,null);
        }
        UserEntity userEntity = userRepository.findByUserNoAndCharacters(userNo,characters);
        if(userEntity==null||!userEntity.getPassword().equals(password)){
            return new Result<>(false, Consts.WRONG_USERNO_OR_PASSWORD,null);
        }

        return new Result<>(true,Consts.LOGIN_SUCCESS,null);
    }

    public Result<Object> register(UserEntity userEntity){
        System.out.println(userEntity);
        return new Result<>(true,Consts.REGISTER_SUCCESS,null);
    }
}
