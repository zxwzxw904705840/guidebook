package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.ReviewEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.BookRepository;
import com.groupname.demo.repository.ClassRepository;
import com.groupname.demo.repository.ReviewRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ClassRepository classRepository;
    @Autowired
    ReviewRepository reviewRepository;

    public Result addClass(ClassEntity classEntity){
        Result result = checkUserPermission(classEntity);
        if(!result.isSuccess()){
            return result;
        }
        result = checkClass(classEntity);
        if(!result.isSuccess()){
            return result;
        }
        /*if(result.getMessage().equals(Consts.GUIDEBOOK_EXISTS)){
            classEntity.setGuidebookStatus(Consts.Status.REVIEWING.getValue());
        }else{
            classEntity.setGuidebookStatus(Consts.Status.DEFAULT.getValue());
        }*/
        //暂定申请开课时不能设置教参
        classEntity.setGuidebook(null);

        classEntity.resetClassNo();
        reviewRepository.save(new ReviewEntity(Consts.ReviewType.CLASS_NO.getValue(),classEntity.getClassNo()));
        classRepository.save(classEntity);
        return new Result(true,Consts.ADD_CLASS_SUCCESS);
    }

    private Result checkClass(ClassEntity classEntity){
        if(classEntity==null){
            return new Result(false,Consts.ADD_CLASS_FAILED);
        }
        /*if(classEntity.getGuidebook()!=null){
            if(classEntity.getGuidebook().getIsbn().length()!=13){
                return new Result(false,Consts.GUIDEBOOK_ISBN_ERROR);
            }
            if(bookRepository.findByIsbn(classEntity.getGuidebook().getIsbn())==null){
                return new Result(false,Consts.GUIDEBOOK_NOT_EXISTS_ERROR);
            }
            return new Result(true,Consts.GUIDEBOOK_EXISTS);
        }*/
        return new Result(true,"");
    }

    private Result checkUserPermission(ClassEntity classEntity){
        if(classEntity==null||classEntity.getTeacher()==null) {
            return new Result(false, Consts.PERMISSION_DENIED);
        }
        UserEntity userEntity = userRepository.findByUserNo(classEntity.getTeacher().getUserNo());
        if(userEntity==null||userEntity.getCharacters()!=Consts.UserType.TEACHER.getValue()||userEntity.getUserStatus()!=Consts.Status.NORMAL.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        return new Result(true, Consts.ACCOUNT_CAN_USE);
    }
}
