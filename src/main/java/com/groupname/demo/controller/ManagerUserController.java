package com.groupname.demo.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public class ManagerUserController {

    @RequestMapping(value="/ShowTeacherInfor",method= RequestMethod.GET)
    public String showTeacherInfor(HttpServletRequest request, ModelMap model){

        return "ManagerUser-Teacher";
    }

    @RequestMapping(value="/ShowStudentInfor",method= RequestMethod.GET)
    public String showStudentInfor(HttpServletRequest request, ModelMap model){

        return "ManagerUser-Student";
    }

    @RequestMapping(value="/ShowManagerInfor",method= RequestMethod.GET)
    public String showManagerInfor(HttpServletRequest request, ModelMap model){

        return "ManagerUser-Manager";
    }
}
