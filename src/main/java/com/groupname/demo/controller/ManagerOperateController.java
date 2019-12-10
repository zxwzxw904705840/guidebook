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
import java.util.LinkedList;
import java.util.List;

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
        List<BookEntity> coursewareInfor = bookRepository.findAll();
        // System.out.println(coursewareInfor);
        for(int i=0;i<coursewareInfor.size();i++)
        {
            BookEntity book=coursewareInfor.get(i);
            System.out.println(book);
            System.out.println(book.getIsbn());
            System.out.println(book.getBookName());
        }
        model.addAttribute("coursewareInfor",coursewareInfor);
        return "ManagerWare";
    }
}
