package com.groupname.demo.controller;

import com.groupname.demo.entity.MajorEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ManagerCheckController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/ShowTeacherSign",method = RequestMethod.GET)
    public String showTeacherSign(HttpServletRequest request, ModelMap model){
        ArrayList<UserEntity> checkTeacher=new ArrayList<>();
        checkTeacher.add(userRepository.findByUserNo("t10011"));
        checkTeacher.add(userRepository.findByUserNo("s10000"));
        model.addAttribute("checkTeacher",checkTeacher);

        return "ManagerCheck-TeacherSign";
    }

    @RequestMapping(value="/ShowBookChange",method=RequestMethod.GET)
    public String showBookChange(HttpServletRequest request,ModelMap model){

        return "ManagerCheck-BookChange";
    }

    @RequestMapping(value="/ShowBookBuy",method=RequestMethod.GET)
    public String showBookBuy(HttpServletRequest request,ModelMap model){

        return "ManagerCheck-BookBuy";
    }

    @RequestMapping(value="/ShowCourseWare",method=RequestMethod.GET)
    public String showCourseWare(HttpServletRequest request,ModelMap model){

        return "ManagerCheck-CourseWare";
    }

    @RequestMapping(value="/ShowMessage",method=RequestMethod.GET)
    public String showMessage(HttpServletRequest request,ModelMap model){

        return "ManagerCheck-Message";
    }
}
