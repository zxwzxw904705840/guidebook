package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.FileEntity;
import com.groupname.demo.entity.ReviewEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.ClassRepository;
import com.groupname.demo.repository.FileRepository;
import com.groupname.demo.repository.ReviewRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClassRepository classRepository;

    public Result uploadFile(MultipartFile file, ClassEntity classToChange, UserEntity userEntity){
        if(userEntity==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(userEntity.getUserNo()==null||userEntity.getUserNo().equals("")){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(file==null||file.isEmpty()){
            return new Result(false,Consts.FILE_UPLOAD_FAILED);
        }
        if(classToChange==null||classToChange.getClassNo()==null){
            return new Result(false,Consts.CLASS_NOT_EXISTS);
        }
        UserEntity teacher = userRepository.findByUserNo(userEntity.getUserNo());
        if(teacher==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(teacher.getCharacters()!=Consts.UserType.TEACHER.getValue()||teacher.getUserStatus()!=Consts.Status.NORMAL.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        ClassEntity classEntity = classRepository.findByClassNo(classToChange.getClassNo());
        if(classEntity==null){
            return new Result(false,Consts.CLASS_NOT_EXISTS);
        }
        if(classEntity.getTeacher()==null||!teacher.getUserNo().equals(classEntity.getTeacher().getUserNo())){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(Consts.FILE_PATH + fileName);
        try {
            file.transferTo(dest);
            FileEntity fileEntity = new FileEntity();
            fileEntity.setClassEntity(classEntity);
            fileEntity.setFilePath(Consts.FILE_PATH);
            fileEntity.resetFileNo();
            reviewRepository.save(new ReviewEntity(Consts.ReviewType.FILE_NO.getValue(),fileEntity.getFileNo()));
            fileRepository.save(fileEntity);
            return new Result(true, Consts.FILE_UPLOAD_SUCCESS);
        } catch (IOException e) {
            return new Result(false,Consts.FILE_UPLOAD_FAILED);
        }


    }
}
