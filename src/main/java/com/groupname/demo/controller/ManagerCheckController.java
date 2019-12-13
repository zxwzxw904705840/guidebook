package com.groupname.demo.controller;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.*;
import com.groupname.demo.repository.*;
import com.groupname.demo.service.ManagerCheckService;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.krb5.internal.PAForUserEnc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManagerCheckController {

    private String userno;
    public String getUserno(){
        return userno;
    }
    public void setUserno(String userno){
        this.userno=userno;
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    ManagerCheckService managerCheckService;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String main(HttpServletRequest request,ModelMap model){

        System.out.println("login");
        String userNo=request.getParameter("userNo");
        String password=request.getParameter("password");
        if(userNo.equals("s10001") && password.equals("123456")){
            return "index";
        }
        else if(userNo.equals("aaa") && password.equals("123456")){
            return showTeacherSign(request,model);
        }
        return "用户名或密码不正确";
    }

    @RequestMapping(value = "/ShowTeacherSign",method = RequestMethod.GET)
    public String showTeacherSign(HttpServletRequest request, ModelMap model){
        ArrayList<UserEntity> checkTeacher=new ArrayList<>();
        checkTeacher=userRepository.findAllByCharacters(Consts.UserType.TEACHER.getValue());
        model.addAttribute("checkTeacher",checkTeacher);

        String userno=request.getParameter("userno");
        System.out.println("响应showTeacherSign");
        System.out.println(userno);
        setUserno(userno);

        UserEntity user=new UserEntity();
        user.setUserNo(userno);
        ReviewEntity review=new ReviewEntity(Consts.ReviewType.USER_NO.getValue(),userno);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");

        Result result=managerCheckService.review(review,true,manager);

        return "ManagerCheck-TeacherSign";
    }

    @RequestMapping(value = "/CancelTeacherSign",method = RequestMethod.GET)
    public String UpdateTeacherSign(HttpServletRequest request, ModelMap model){

        System.out.println("CancelTeacherSign");
        String userno=request.getParameter("userno");

        UserEntity user=new UserEntity();
        user.setUserNo(userno);
        ReviewEntity review=new ReviewEntity(Consts.ReviewType.USER_NO.getValue(),userno);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");

        Result result=managerCheckService.review(review,false,manager);
        if(result.isSuccess()){
            System.out.println("成功修改");
            return showTeacherSign(request,model);
        }
        else{
            System.out.println(result.getMessage());
            return "404";
        }



    }

    @RequestMapping(value="/ShowBookChange",method=RequestMethod.GET)
    public String showBookChange(HttpServletRequest request,ModelMap model){
        List<ClassEntity> changeBook=new ArrayList<>();
        changeBook=classRepository.findAll();
        model.addAttribute("changeBook",changeBook);

        String classNo=request.getParameter("classNo");

        ReviewEntity review=new ReviewEntity(Consts.ReviewType.CLASS_NO.getValue(),classNo);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");
        Result result=managerCheckService.review(review,true,manager);

        return "ManagerCheck-BookChange";
    }

    @RequestMapping(value="/CancelBookBuy",method=RequestMethod.GET)
    public String cancelBookBuy(HttpServletRequest request,ModelMap model){


        String purchaseNo=request.getParameter("purchaseNo");

        ReviewEntity review=new ReviewEntity(Consts.ReviewType.PURCHASE_NO.getValue(),purchaseNo);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");
        Result result=managerCheckService.review(review,false,manager);

        return showBookBuy(request,model);
    }

    @RequestMapping(value="/CancelBookChange",method=RequestMethod.GET)
    public String cancelBookChange(HttpServletRequest request,ModelMap model){


        String classNo=request.getParameter("classNo");

        ReviewEntity review=new ReviewEntity(Consts.ReviewType.CLASS_NO.getValue(),classNo);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");
        Result result=managerCheckService.review(review,false,manager);

        return showBookChange(request,model);
    }

    @RequestMapping(value="/ShowBookBuy",method=RequestMethod.GET)
    public String showBookBuy(HttpServletRequest request,ModelMap model){
        List<PurchaseEntity> buyBook=purchaseRepository.findAll();
        model.addAttribute("buyBook",buyBook);

        String purchaseNo=request.getParameter("purchaseNo");

        ReviewEntity review=new ReviewEntity(Consts.ReviewType.PURCHASE_NO.getValue(),purchaseNo);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");
        Result result=managerCheckService.review(review,true,manager);

        return "ManagerCheck-BookBuy";
    }

    @RequestMapping(value="/ShowCourseWare",method=RequestMethod.GET)
    public String showCourseWare(HttpServletRequest request,ModelMap model){

        return "ManagerCheck-CourseWare";
    }

    @RequestMapping(value="/ShowMessage",method=RequestMethod.GET)
    public String showMessage(HttpServletRequest request,ModelMap model){
        List<MessageEntity> message=messageRepository.findAll();
        model.addAttribute("message",message);

        String messageNo=request.getParameter("messageNo");

        ReviewEntity review=new ReviewEntity(Consts.ReviewType.MESSAGE_NO.getValue(),messageNo);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");
        Result result=managerCheckService.review(review,true,manager);

        return "ManagerCheck-Message";
    }

    @RequestMapping(value="/CancelMessage",method=RequestMethod.GET)
    public String cancelMessage(HttpServletRequest request,ModelMap model){

        String messageNo=request.getParameter("messageNo");

        ReviewEntity review=new ReviewEntity(Consts.ReviewType.MESSAGE_NO.getValue(),messageNo);
        UserEntity manager=new UserEntity();
        manager.setUserNo("aaa");
        Result result=managerCheckService.review(review,false,manager);

        return showMessage(request,model);
    }
}
