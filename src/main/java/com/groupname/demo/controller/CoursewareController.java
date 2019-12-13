package com.groupname.demo.controller;

import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class CoursewareController {

    @Autowired
    ClassRepository classRepository;

    @RequestMapping(value = "/WareCenter",method = RequestMethod.GET)
    public String WareCenter(HttpServletRequest request, ModelMap model){
        return "courseWave";
    }

    @RequestMapping(value = "/ShowCourseInfor-search",method = RequestMethod.GET)
    public String ShowCourseInfor(HttpServletRequest request, ModelMap model){
        String courseName=request.getParameter("courseName");
        String teacherName=request.getParameter("teacherName");


        ArrayList<ClassEntity> classEntities=classRepository.findAll();
        ArrayList<ClassEntity> courseInfor=new ArrayList<>();
        for(int i=0;i<classEntities.size();i++)
        {
            ClassEntity classEntity=classEntities.get(i);
            if(classEntity.getTeacher().getUserName().equals(teacherName)){
                if(classEntity.getCourse().getCourseName().equals(courseName)){
                    courseInfor.add(classEntity);
                }
            }
        }
       // ShowCourseInfor_search(courseInfor,request,model);
        model.addAttribute("courseInfor",courseInfor);
        model.addAttribute("teacherName",teacherName);
        return "courseWave-search";
    }

    /*@RequestMapping(value = "/ShowCourseInfor-search",method = RequestMethod.GET)
    public String ShowCourseInfor_search(ArrayList<ClassEntity> courseInfor,
                                         HttpServletRequest request,ModelMap model){

        System.out.println("响应ShowCourseInfor-search");
        System.out.println(courseInfor);
        return "courseWave-search";
    }*/

}
