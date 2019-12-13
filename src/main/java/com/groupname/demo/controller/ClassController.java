package com.groupname.demo.controller;

import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.CourseEntity;
import com.groupname.demo.repository.ClassRepository;
import com.groupname.demo.repository.CourseRepository;
import com.groupname.demo.service.UserOperateService;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ClassController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserOperateService userOperateService;

    @RequestMapping(value="/ShowCourseInfor2",method= RequestMethod.GET)
    public String showCourseInfor(HttpServletRequest request, ModelMap model){
        System.out.println("响应ShowCourseInfor2");
        ArrayList<ClassEntity> courseInfor=classRepository.findAll();
        model.addAttribute("courseInfor",courseInfor);
        return "class";
    }

    @RequestMapping(value="/ShowClass1",method= RequestMethod.GET)
    public String ShowClass1(HttpServletRequest request, ModelMap model){
        System.out.println("响应ShowClass1");
        ArrayList<CourseEntity> courseEntities=courseRepository.findAllByTerm(1);
        ArrayList<ClassEntity> courseInfor=new ArrayList<>();
        for(int i=0;i<courseEntities.size();i++)
        {
            CourseEntity courseEntity=courseEntities.get(i);
            courseInfor.addAll(classRepository.findAllByCourse(courseEntity));
        }
        model.addAttribute("courseInfor",courseInfor);
        return "class-one";
    }

    @RequestMapping(value="/ShowClass2",method= RequestMethod.GET)
    public String ShowClass2(HttpServletRequest request, ModelMap model){
        System.out.println("响应ShowClass2");
        ArrayList<CourseEntity> courseEntities=courseRepository.findAllByTerm(2);
        ArrayList<ClassEntity> courseInfor=new ArrayList<>();
        for(int i=0;i<courseEntities.size();i++)
        {
            CourseEntity courseEntity=courseEntities.get(i);
            courseInfor.addAll(classRepository.findAllByCourse(courseEntity));
        }
        model.addAttribute("courseInfor",courseInfor);
        return "class-two";
    }

    @RequestMapping(value="/ShowClass3",method= RequestMethod.GET)
    public String ShowClass3(HttpServletRequest request, ModelMap model){
        System.out.println("响应ShowClass3");
        ArrayList<CourseEntity> courseEntities=courseRepository.findAllByTerm(3);
        ArrayList<ClassEntity> courseInfor=new ArrayList<>();
        for(int i=0;i<courseEntities.size();i++)
        {
            CourseEntity courseEntity=courseEntities.get(i);
            courseInfor.addAll(classRepository.findAllByCourse(courseEntity));
        }
        model.addAttribute("courseInfor",courseInfor);
        return "class-three";
    }

    @RequestMapping(value="/ShowClass4",method= RequestMethod.GET)
    public String ShowClass4(HttpServletRequest request, ModelMap model){
        System.out.println("响应ShowClass4");
        ArrayList<CourseEntity> courseEntities=courseRepository.findAllByTerm(4);
        ArrayList<ClassEntity> courseInfor=new ArrayList<>();
        for(int i=0;i<courseEntities.size();i++)
        {
            CourseEntity courseEntity=courseEntities.get(i);
            courseInfor.addAll(classRepository.findAllByCourse(courseEntity));
        }
        model.addAttribute("courseInfor",courseInfor);
        return "class-four";
    }

    @RequestMapping(value="/SearchClass",method= RequestMethod.GET)
    public String SearchClass(HttpServletRequest request, ModelMap model){
        String className=request.getParameter("input");
        System.out.println(className);
        ArrayList<CourseEntity> courseEntities=courseRepository.findAllByCourseNameLike(className);
        System.out.println(courseEntities);
        ArrayList<ClassEntity> courseInfor=new ArrayList<>();
        for(int i=0;i<courseEntities.size();i++)
        {
            CourseEntity courseEntity=courseEntities.get(i);
            courseInfor.addAll(classRepository.findAllByCourse(courseEntity));
        }
        // model.addAttribute("courseInfor",courseInfor);
        return SearchClassResult(courseInfor,request,model);
    }

    @RequestMapping(value="/SearchClassResult",method= RequestMethod.GET)
    public String SearchClassResult(ArrayList<ClassEntity> courseInfor,
                                    HttpServletRequest request, ModelMap model){
        model.addAttribute("courseInfor",courseInfor);
        System.out.println("响应SearchClassResult");
        System.out.println(courseInfor);

        return "class";
    }
}
