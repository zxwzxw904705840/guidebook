package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ManagerUserService {
    @Autowired
    UserRepository userRepository;
    /*
    查找用户
     */
    public Result<ArrayList<UserEntity>> getNormalUserByCharacters(Integer characters,UserEntity manager){
        Result<ArrayList<UserEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        if(characters<0||characters>=Consts.UserType.values().length){
            return new Result<>(false,Consts.USER_CHARACTER_ERROR);
        }
        ArrayList<UserEntity> userEntityArrayList=userRepository.findAllByUserStatusAndCharacters(Consts.Status.NORMAL.getValue(),characters);
        return new Result<>(true,Consts.INQUIRE_SUCCESS,userEntityArrayList);
    }
    public Result<ArrayList<UserEntity>> getNormalUser(UserEntity manager){
        Result<ArrayList<UserEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        ArrayList<UserEntity> userEntityArrayList=userRepository.findAllByUserStatus(Consts.Status.NORMAL.getValue());
        return new Result<>(true,Consts.INQUIRE_SUCCESS,userEntityArrayList);
    }
    public Result<ArrayList<UserEntity>> getNormalUserByUserNoLike(String userNo,UserEntity manager){
        Result<ArrayList<UserEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        if(userNo==null||userNo.equals("")){
            return getNormalUser(manager);
        }
        userNo="%"+userNo+"%";
        ArrayList<UserEntity> userEntityArrayList=userRepository.findAllByUserStatusAndUserNoLike(Consts.Status.NORMAL.getValue(),userNo);
        return new Result<>(true,Consts.INQUIRE_SUCCESS,userEntityArrayList);
    }
    /*
    TODO:用户信息修改和删除
     */
    private Result checkUserPermission(UserEntity user){
        if(user==null||user.getUserNo()==null||user.getUserNo().equals("")) {
            return new Result(false, Consts.PERMISSION_DENIED);
        }
        UserEntity userEntity = userRepository.findByUserNo(user.getUserNo());
        if(userEntity==null||userEntity.getCharacters()!=Consts.UserType.MANAGER.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        return new Result(true, Consts.ACCOUNT_CAN_USE);
    }
}
