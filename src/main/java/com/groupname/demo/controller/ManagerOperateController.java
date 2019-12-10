package com.groupname.demo.controller;

import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ManagerOperateController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value="/ShowCourseInfor",method= RequestMethod.GET)
    public String showCourseInfor(HttpServletRequest request, ModelMap model){

        return "ManagerCourse";
    }

    @RequestMapping(value="/GetStudentMessage",method= RequestMethod.GET)
    public String showMessage(HttpServletRequest request, ModelMap model){

        return "ManagerMessage";
    }

    @RequestMapping(value="/ShowCoursewareInfor",method= RequestMethod.GET)
    public String showCourseWare(HttpServletRequest request, ModelMap model){
       /* ArrayList<BookEntity> coursewareInfor=new ArrayList<>();
        coursewareInfor.add(bookRepository.)*/
        return "ManagerWare";
    }
}
