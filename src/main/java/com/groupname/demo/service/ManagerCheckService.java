package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.MessageEntity;
import com.groupname.demo.entity.PurchaseEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.ClassRepository;
import com.groupname.demo.repository.MessageRepository;
import com.groupname.demo.repository.PurchaseRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ManagerCheckService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ClassRepository classRepository;

    /*
    查询用户审核列表
     */
    public Result<ArrayList<UserEntity>> getUserReviewList(UserEntity manager){
        Result<ArrayList<UserEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        ArrayList<UserEntity> userEntityArrayList = userRepository.findAllByUserStatus(Consts.Status.REVIEWING.getValue());
        return new Result<>(true,Consts.INQUIRE_SUCCESS,userEntityArrayList);
    }
    /*
    查询留言审核列表
     */
    public Result<ArrayList<MessageEntity>> getMessageReviewList(UserEntity manager){
        Result<ArrayList<MessageEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        ArrayList<MessageEntity> messageEntityArrayList = messageRepository.findAllByMessageStatus(Consts.Status.REVIEWING.getValue());
        return new Result<>(true,Consts.INQUIRE_SUCCESS,messageEntityArrayList);
    }
    /*
    查询采购审核列表
     */
    public Result<ArrayList<PurchaseEntity>> getPurchaseReviewList(UserEntity manager){
        Result<ArrayList<PurchaseEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        ArrayList<PurchaseEntity> purchaseEntityArrayList = purchaseRepository.findAllByPurchaseStatus(Consts.PurchaseStatus.REVIEWING.getValue());
        return new Result<>(true,Consts.INQUIRE_SUCCESS,purchaseEntityArrayList);
    }
    /*
    查询教参修改审核列表
     */
    public Result<ArrayList<ClassEntity>> getGuidebookChangeReviewList(UserEntity manager){
        Result<ArrayList<ClassEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        ArrayList<ClassEntity> classEntityArrayList = classRepository.findAllByGuidebookStatus(Consts.Status.REVIEWING.getValue());
        return new Result<>(true,Consts.INQUIRE_SUCCESS,classEntityArrayList);
    }
    /*
    TODO:查询课件审核列表
     */

    private Result checkUserPermission(UserEntity user){
        if(user==null||user.getUserNo()==null) {
            return new Result(false, Consts.PERMISSION_DENIED);
        }
        UserEntity userEntity = userRepository.findByUserNo(user.getUserNo());
        if(userEntity==null||userEntity.getCharacters()!=Consts.UserType.MANAGER.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        return new Result(true, Consts.ACCOUNT_CAN_USE);
    }
}
