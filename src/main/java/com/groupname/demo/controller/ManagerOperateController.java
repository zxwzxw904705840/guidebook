package com.groupname.demo.controller;

import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.CourseEntity;
import com.groupname.demo.entity.MessageEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.BookRepository;
import com.groupname.demo.repository.CourseRepository;
import com.groupname.demo.repository.MessageRepository;
import com.groupname.demo.service.ManagerOperateService;
import com.groupname.demo.service.MessageService;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManagerOperateController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ManagerOperateService managerOperateService;

    @RequestMapping(value="/ShowCourseInfor",method= RequestMethod.GET)
    public String showCourseInfor(HttpServletRequest request, ModelMap model){
        List<CourseEntity> courseInfor=courseRepository.findAll();
        model.addAttribute("courseInformation",courseInfor);
        return "ManagerCourse";
    }

    @RequestMapping(value="/GetMessage",method= RequestMethod.GET)
    public String showMessage(HttpServletRequest request, ModelMap model){
        // 返回已经审核好的留言

        UserEntity userEntity=new UserEntity();
        userEntity.setUserNo("aaa");
        Result<ArrayList<MessageEntity>> result =managerOperateService.getMessage(userEntity);
        if(result.isSuccess()){
            ArrayList<MessageEntity> allmessage=result.getObject();
            model.addAttribute("allmessage",allmessage);
            return "ManagerMessage";
        }
        else{
            System.out.println(result.getMessage());
            return "404";
        }
    }

    @RequestMapping(value="/ShowCoursewareInfor",method= RequestMethod.GET)
    public String showCourseWare(
                                 HttpServletRequest request, ModelMap model){
        List<BookEntity> coursewareInfor = bookRepository.findAll();
        model.addAttribute("coursewareInfor",coursewareInfor);
        System.out.println("响应ShowCoursewareInfor");
        //System.out.println(bookname);
        return "ManagerWare";
    }

    @RequestMapping(value="/ShowCoursewareInfor_Maths",method= RequestMethod.GET)
    public String showCourseWare_maths(HttpServletRequest request, ModelMap model){
        List<BookEntity> coursewareInfor=bookRepository.findAllByAuthorMajor(0);
        model.addAttribute("coursewareInfor",coursewareInfor);
        System.out.println("响应ShowCoursewareInfor_Maths");
        return "ManagerWare_Maths";
    }

    @RequestMapping(value="/ShowCoursewareInfor_CS",method= RequestMethod.GET)
    public String showCourseWare_cs(HttpServletRequest request, ModelMap model){
        List<BookEntity> coursewareInfor=bookRepository.findAllByAuthorMajor(1);
        model.addAttribute("coursewareInfor",coursewareInfor);
        System.out.println("响应ShowCoursewareInfor_CS");
        return "ManagerWare_CS";
    }

    @RequestMapping(value="/ShowCoursewareInfor_Chemistry",method= RequestMethod.GET)
    public String showCourseWare_chemistry(HttpServletRequest request, ModelMap model){
        List<BookEntity> coursewareInfor=bookRepository.findAllByAuthorMajor(2);
        model.addAttribute("coursewareInfor",coursewareInfor);
        System.out.println("响应ShowCoursewareInfor_Chemistry");
        return "ManagerWare_Chemistry";
    }

    @RequestMapping(value="/ShowCoursewareInfor_Physics",method= RequestMethod.GET)
    public String showCourseWare_physics(HttpServletRequest request, ModelMap model){
        List<BookEntity> coursewareInfor=bookRepository.findAllByAuthorMajor(3);
        model.addAttribute("coursewareInfor",coursewareInfor);
        System.out.println("响应ShowCoursewareInfor_Physics");
        return "ManagerWare_Physics";
    }

    @RequestMapping(value="/ShowCoursewareInfor_Human",method= RequestMethod.GET)
    public String showCourseWare_human(HttpServletRequest request, ModelMap model){
        List<BookEntity> coursewareInfor=bookRepository.findAllByAuthorMajor(4);
        model.addAttribute("coursewareInfor",coursewareInfor);
        System.out.println("响应ShowCoursewareInfor_Human");
        return "ManagerWare_Human";
    }

    @RequestMapping(value="/ShowCoursewareInfor_Art",method= RequestMethod.GET)
    public String showCourseWare_art(HttpServletRequest request, ModelMap model){
        List<BookEntity> coursewareInfor=bookRepository.findAllByAuthorMajor(5);
        model.addAttribute("coursewareInfor",coursewareInfor);
        System.out.println("响应ShowCoursewareInfor_Art");
        return "ManagerWare_art";
    }

    /*
    @RequestMapping(value="/Update_Ware",method= RequestMethod.POST)
    public String updateWare(@RequestParam("inline_bookPage") String bookPage,
                             @RequestParam("bookName") String bookName,
                            // @RequestParam("inline_isbn") String isbn,
                            // @RequestParam("inline_major") String major,
                            // @RequestParam("inline_bookNumber") String bookNumber,
                             HttpServletResponse response,HttpServletRequest request, ModelMap model){
        System.out.println("响应updateWare");
        System.out.println(bookName);
        return showCourseWare(request,model);
    }*/
}
