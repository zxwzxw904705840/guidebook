package com.groupname.demo.controller;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.service.LoginService;
import com.groupname.demo.service.ManagerUserService;
import com.groupname.demo.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ManagerUserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    ManagerUserService managerUserService;

    @RequestMapping(value="/ShowTeacherInfor",method= RequestMethod.GET)
    public String showTeacherInfor(HttpServletRequest request, ModelMap model){
        ArrayList<UserEntity> teacher=new ArrayList<>();
        teacher=userRepository.findAllByUserStatusAndCharacters(Consts.Status.NORMAL.getValue(),Consts.UserType.TEACHER.getValue());
        //teacher=userRepository.findAllByCharacters(Consts.UserType.STUDENT.getValue());
        model.addAttribute("teacherinfor",teacher);

        /*
        删除老师信息
         */
        String userNo=request.getParameter("userNo");
        UserEntity user=new UserEntity();
        user.setUserNo(userNo);
        user.setUserStatus(Consts.Status.DELETED.getValue());
        UserEntity manager=new UserEntity();manager.setUserNo("aaa");
        Result result=managerUserService.updateNormalUser(user,manager);
        System.out.println(result.getMessage());
        return "ManagerUser-Teacher";
    }

    @RequestMapping(value="/ShowStudentInfor",method= RequestMethod.GET)
    public String showStudentInfor(HttpServletRequest request, ModelMap model){
        ArrayList<UserEntity> student=new ArrayList<>();
        student=userRepository.findAllByUserStatusAndCharacters(Consts.Status.NORMAL.getValue(),Consts.UserType.STUDENT.getValue());
        student=userRepository.findAllByCharacters(Consts.UserType.STUDENT.getValue());
        //model.addAttribute("studentinfor",student);

        /*
        删除学生信息
         */
        String userNo=request.getParameter("userNo");
        UserEntity user=new UserEntity();user.setUserNo(userNo);
        user.setUserStatus(Consts.Status.DELETED.getValue());
        UserEntity manager=new UserEntity();manager.setUserNo("aaa");
        Result result=managerUserService.updateNormalUser(user,manager);

        return "ManagerUser-Student";
    }

    @RequestMapping(value="/ShowManagerInfor",method= RequestMethod.GET)
    public String showManagerInfor(HttpServletRequest request, ModelMap model){

        return "ManagerUser-Manager";
    }

    @RequestMapping(value="/ModifySelf",method = RequestMethod.GET)
    public String modifySelf(HttpServletRequest request, ModelMap model){
        UserEntity manager=userRepository.findByUserNo("aaa");
        model.addAttribute("userInfor",manager);

        /*
        修改资料
         */
        String old=request.getParameter("password_old");
        String new1=request.getParameter("password_1");
        String new2=request.getParameter("password_2");




        return "ManagerInfor";
    }

}
