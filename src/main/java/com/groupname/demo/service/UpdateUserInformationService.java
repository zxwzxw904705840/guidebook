package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.MajorRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserInformationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MajorRepository majorRepository;

    public Result updateSelfInformation(UserEntity userEntity){
        Result result = checkUser(userEntity);
        if(!result.isSuccess()){
            return result;
        }
        userRepository.save(userEntity);
        return new Result(true,Consts.UPDATE_USER_SUCCESS);
    }

    private Result checkUser(UserEntity user){
        if (user==null){
            return new Result<>(false, Consts.USERNO_NOT_EXISTS);
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
            return new Result<>(false,Consts.USER_CHARACTER_ERROR);
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
        if(userEntity==null || userEntity.getUserStatus()!=Consts.Status.NORMAL.getValue()){
            return new Result<>(false,Consts.USERNO_NOT_EXISTS);
        }
        if(user.getMajor()==null||user.getMajor().getMajorNo()==null||majorRepository.findByMajorNo(user.getMajor().getMajorNo())==null){
            return new Result(false,Consts.ILLEGAL_MAJOR);
        }
        return new Result<>(true,Consts.ACCOUNT_CAN_USE);
    }
}
