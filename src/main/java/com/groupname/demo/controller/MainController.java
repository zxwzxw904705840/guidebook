package com.groupname.demo.controller;

import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.ClassRepository;
import com.groupname.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/ShowTeacherCourseInfor",method=RequestMethod.GET)
    public String ShowTeacherCourseInfor(HttpServletRequest request, ModelMap model){
        UserEntity teacher=userRepository.findByUserNo("s10001");
        ArrayList<ClassEntity> classEntities=classRepository.findAllByTeacher(teacher);
        model.addAttribute("teacherCourseInfor",classEntities);
        return "TeacherCourse";
    }


}
