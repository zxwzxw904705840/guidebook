package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.*;
import com.groupname.demo.repository.*;
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
    @Autowired
    ReviewRepository reviewRepository;

    /*
    查询用户审核列表
     */
    public Result<ArrayList<UserEntity>> getUserReviewList(UserEntity manager){
        Result<ArrayList<UserEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        ArrayList<UserEntity> userEntityArrayList = userRepository.findAllByCharacters(Consts.UserType.TEACHER.getValue());
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
        ArrayList<PurchaseEntity> purchaseEntityArrayList = purchaseRepository.findAll();
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
    /*
    TODO:课件审核
     */
    /*
    所有的审核操作
     */
    public Result review(ReviewEntity reviewEntity,boolean passed,UserEntity manager){
        Result<ArrayList<ClassEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        if(reviewEntity==null){
            return new Result(false,Consts.REVIEW_FAILED);
        }
        if(reviewEntity.getReviewObjectNo()==null||reviewEntity.getReviewObjectNo().equals("")){
            return new Result(false,Consts.REVIEW_FAILED);
        }
        ReviewEntity review = reviewRepository.findByReviewObjectNo(reviewEntity.getReviewObjectNo());
        if(review==null){
            review = new ReviewEntity();
            review.setReviewObjectNo(reviewEntity.getReviewObjectNo());
            review.setReviewType(reviewEntity.getReviewType());
            review.resetReviewNo();
        }
        review.setReviewer(manager);
        switch (review.getReviewType()){
            case 0://用户审核
                UserEntity user = userRepository.findByUserNo(review.getReviewObjectNo());
                if(user==null){
                    return new Result(false,Consts.REVIEW_FAILED);
                }
                if(user.getUserStatus()!=Consts.Status.REVIEWING.getValue()){
                    return new Result(false,Consts.STATUS_ERROR);
                }
                if(passed){
                    user.setUserStatus(Consts.Status.NORMAL.getValue());
                }else {
                    user.setUserStatus(Consts.Status.REVIEW_FAILED.getValue());
                }
                userRepository.save(user);
                reviewRepository.save(review);
                return new Result(true,Consts.REVIEW_SUCCESS);
            case 1://课程教参修改审核
                ClassEntity classEntity=classRepository.findByClassNo(review.getReviewObjectNo());
                if (classEntity == null) {
                    return new Result(false,Consts.REVIEW_FAILED);
                }
                if(classEntity.getGuidebookStatus()!=Consts.Status.REVIEWING.getValue()){
                    return new Result(false,Consts.STATUS_ERROR);
                }
                if(passed){
                    classEntity.setGuidebookStatus(Consts.Status.NORMAL.getValue());
                }else {
                    classEntity.setGuidebookStatus(Consts.Status.REVIEW_FAILED.getValue());
                }
                classRepository.save(classEntity);
                reviewRepository.save(review);
                return new Result(true,Consts.REVIEW_SUCCESS);
            case 2://课件审核
                return new Result(false,Consts.PERMISSION_DENIED);
            case 3://留言审核
                MessageEntity message=messageRepository.findByMessageNo(review.getReviewObjectNo());
                if(message==null||message.getMessageStatus()==Consts.Status.DELETED.getValue()){
                    return new Result(false,Consts.REVIEW_FAILED);
                }
                if(message.getMessageStatus()!=Consts.Status.REVIEWING.getValue()){
                    return new Result(false,Consts.STATUS_ERROR);
                }
                if(passed){
                    message.setMessageStatus(Consts.Status.NORMAL.getValue());
                }else {
                    message.setMessageStatus(Consts.Status.REVIEW_FAILED.getValue());
                }
                messageRepository.save(message);
                reviewRepository.save(review);
                return new Result(true,Consts.REVIEW_SUCCESS);
            case 4://采购申请审核
                PurchaseEntity purchase=purchaseRepository.findByPurchaseNo(review.getReviewObjectNo());
                if(purchase==null){
                    return new Result(false,Consts.REVIEW_FAILED);
                }
                if(purchase.getPurchaseStatus()!=Consts.PurchaseStatus.REVIEWING.getValue()){
                    return new Result(false,Consts.STATUS_ERROR);
                }
                if(passed){
                    purchase.setPurchaseStatus(Consts.PurchaseStatus.PURCHASING.getValue());
                }else {
                    purchase.setPurchaseStatus(Consts.PurchaseStatus.REVIEW_FAILED.getValue());
                }
                purchaseRepository.save(purchase);
                reviewRepository.save(review);
                return new Result(true,Consts.REVIEW_SUCCESS);
            default: return new Result(false,Consts.REVIEW_TYPE_ERROR);

        }
    }


    @Deprecated
    public Result reviewMessage(String messageNo,boolean passed,UserEntity manager){
        Result<ArrayList<ClassEntity>> result = checkUserPermission(manager);
        if(!result.isSuccess()){
            return result;
        }
        if(messageNo==null||messageNo.equals("")){
            return new Result(false,Consts.MESSAGE_NO_NOT_EXISTS);
        }
        MessageEntity message = messageRepository.findByMessageNo(messageNo);
        if(message==null||message.getMessageStatus()==Consts.Status.DELETED.getValue()){
            return new Result(false,Consts.MESSAGE_NO_NOT_EXISTS);
        }
        if(message.getMessageStatus()!=Consts.Status.REVIEWING.getValue()){
            return new Result(false,Consts.MESSAGE_STATUS_ERROR);
        }
        ReviewEntity reviewEntity = reviewRepository.findByReviewObjectNo(messageNo);
        if(reviewEntity==null){
            message.setMessageStatus(Consts.Status.DELETED.getValue());
            messageRepository.save(message);
            return new Result(false,Consts.MESSAGE_STATUS_ERROR);
        }
        if(passed){
            message.setMessageStatus(Consts.Status.NORMAL.getValue());
        }else {
            message.setMessageStatus(Consts.Status.REVIEW_FAILED.getValue());
        }
        reviewEntity.setReviewer(manager);
        messageRepository.save(message);
        reviewRepository.save(reviewEntity);
        return new Result(true,Consts.REVIEW_SUCCESS);
    }


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
