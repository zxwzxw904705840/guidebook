package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.ReviewEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.MajorRepository;
import com.groupname.demo.repository.ReviewRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.hibernate.Session;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Struct;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MajorRepository majorRepository;

    public Result<UserEntity> Login(String userNo, String password){
        if(userNo==null||userNo.equals("")){
            return new Result<>(false,Consts.USERNO_IS_EMPTY);
        }
        if(password==null||password.equals("")){
            return new Result<>(false,Consts.PASSWORD_IS_EMPTY);
        }
        if(userNo.length()>10){
            return new Result<>(false, Consts.WRONG_USERNO_OR_PASSWORD);
        }
        UserEntity userEntity = userRepository.findByUserNo(userNo);
        if(userEntity==null||!userEntity.getPassword().equals(password)){
            return new Result<>(false, Consts.WRONG_USERNO_OR_PASSWORD);
        }
        if(userEntity.getUserStatus()!=Consts.Status.NORMAL.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }

        return new Result<>(true,Consts.LOGIN_SUCCESS,userEntity);
    }

    public Result register(UserEntity user){
        Result result = checkUser(user);
        if(!result.isSuccess()){
            return result;
        }
        /*
        如果是学生，直接通过注册
         */
        if(user.getCharacters()==Consts.UserType.STUDENT.getValue()){
            user.setUserStatus(Consts.Status.NORMAL.getValue());
            userRepository.save(user);
            return new Result<>(true,Consts.REGISTER_SUCCESS);
        }
        /*
        如果是教师，加入审核队列
         */
        user.setUserStatus(Consts.Status.REVIEWING.getValue());
        userRepository.save(user);
        reviewRepository.save(new ReviewEntity(Consts.ReviewType.USER_NO.getValue(),user.getUserNo()));
        return new Result<>(true,Consts.REGISTER_WAITING_FOR_REVIEW);
    }

    private Result checkUser(UserEntity user){
        if (user==null){
            return new Result<>(false,Consts.REGISTER_FAIL);
        }
        if(user.getUserNo()==null||user.getUserNo().equals("")){
            return new Result<>(false,Consts.USERNO_IS_EMPTY);
        }
        if(user.getUserNo().length()>10){
            return new Result<>(false,Consts.USERNO_IS_TOO_LONG);
        }
        if(user.getPassword()==null||user.getPassword().equals("")){
            return new Result<>(false,Consts.PASSWORD_IS_EMPTY);
        }
        if(user.getPassword().length()<6){
            return new Result<>(false,Consts.PASSWORD_TOO_SHORT);
        }
        if(user.getPassword().length()>16){
            return new Result<>(false,Consts.PASSWORD_TOO_LONG);
        }
        if(user.getCharacters()!=0&&user.getCharacters()!=1){
            return new Result<>(false,Consts.REGISTER_FAIL);
        }
        if(user.getUserName()==null||user.getUserName().equals("")){
            return new Result<>(false,Consts.USERNAME_IS_EMPTY);
        }
        if(user.getPhone()==null||user.getPhone().equals("")){
            return new Result<>(false,Consts.PHONE_IS_EMPTY);
        }
        if(user.getPhone().length()!=11){
            return new Result<>(false,Consts.ILLEGAL_TELEPHONE);
        }
        try{
            System.out.println(Integer.parseInt("123456789"));
            //Integer.parseInt(user.getPhone());
        }catch (Exception e){
            return new Result<>(false,Consts.ILLEGAL_TELEPHONE);
        }
        UserEntity userEntity = userRepository.findByUserNo(user.getUserNo());
        if(userEntity!=null && userEntity.getUserStatus()==Consts.Status.NORMAL.getValue()){
            return new Result<>(false,Consts.USERNO_EXISTS);
        }
        if(user.getMajor()==null||user.getMajor().getMajorNo()==null||majorRepository.findByMajorNo(user.getMajor().getMajorNo())==null){
            return new Result(false,Consts.ILLEGAL_MAJOR);
        }
        return new Result<>(true,Consts.ACCOUNT_CAN_USE);
    }
}
