package com.groupname.demo.controller;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.FileService;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class fileController {
    @Autowired
    FileService fileService;
    @RequestMapping(value = "/uploadFile",method = RequestMethod.GET)
    public String uploadFile(HttpServletRequest request){return "uploadFileTest";}

    @RequestMapping(value = "/doUploadFile",method = RequestMethod.POST)
    @ResponseBody
    public String doUploadFile(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        HttpSession session=request.getSession(true);
        String userNo=null;
        if(session.getAttribute("userNo")!=null){
            userNo = session.getAttribute("userNo").toString();
        }
        System.out.println(userNo);
        /*if (file.isEmpty()) {
            return userNo+"上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\90470\\Desktop\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return userNo+fileName+"上传成功";
        } catch (IOException e) {
            return userNo+fileName+"上传失败";
        }*/

        //这里的ClassNo应从表单中获取，这里只是举个例子
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassNo("18b919a2a60e5f7e1cdea02ee3204afb");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo(userNo);
        Result result=fileService.uploadFile(file,classEntity,userEntity);
        if(result.isSuccess()){
            return "上传成功";
        }else{
            return result.getMessage();
        }
        //return "doUploadFile";
    }
}
