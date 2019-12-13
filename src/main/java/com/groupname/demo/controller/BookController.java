package com.groupname.demo.controller;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.repository.BookRepository;
import com.groupname.demo.service.ManagerOperateService;
import com.groupname.demo.service.UserOperateService;
import com.groupname.demo.utils.BookName;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ManagerOperateService managerOperateService;

    @Autowired
    UserOperateService userOperateService;

    @RequestMapping(value = "/SearchA",method = RequestMethod.GET)
    public String SearchA(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='a'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    @RequestMapping(value = "/SearchB",method = RequestMethod.GET)
    public String SearchB(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='b'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    @RequestMapping(value = "/SearchC",method = RequestMethod.GET)
    public String SearchC(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='c'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    @RequestMapping(value = "/SearchG",method = RequestMethod.GET)
    public String SearchG(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='g'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    @RequestMapping(value = "/SearchS",method = RequestMethod.GET)
    public String SearchS(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='s'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    @RequestMapping(value = "/SearchD",method = RequestMethod.GET)
    public String SearchD(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='d'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    @RequestMapping(value = "/SearchX",method = RequestMethod.GET)
    public String SearchX(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='x'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    @RequestMapping(value = "/SearchY",method = RequestMethod.GET)
    public String SearchY(HttpServletRequest request, ModelMap model){
        ArrayList<BookEntity> allBook=bookRepository.findAll();
        ArrayList<BookEntity> resultBook=new ArrayList<>();
        for(int i=0;i<allBook.size();i++)
        {
            BookEntity bookEntity=allBook.get(i);
            String bookName=bookEntity.getBookName();
            char titleName=getBookName(bookName);
            if(titleName=='y'){
                resultBook.add(bookEntity);
            }
        }
        return ShowBookInforResult(resultBook,request,model);
    }

    public static char getBookName(String bookName){
        String[] bookNameList = bookName.split("\\|");
        char result=bookNameList[bookNameList.length-1].charAt(0);
        return result;
    }

    @RequestMapping(value = "/SearchBookName",method = RequestMethod.GET)
    public String SearchBookName(HttpServletRequest request, ModelMap model){
        String input=request.getParameter("input");
        System.out.println("响应SearchBookName");
        System.out.println(input);
        Result<ArrayList<BookEntity>> result=userOperateService.getAllBookByBookNameLikeOrIsbn(input);
        ArrayList<BookEntity> book=result.getObject();
        System.out.println(book);
        return ShowBookInforResult(book,request,model);
    }

    @RequestMapping(value = "/ShowBookInforResult",method = RequestMethod.GET)
    public String ShowBookInforResult(ArrayList<BookEntity> bookInfor,
                                      HttpServletRequest request, ModelMap model){

        model.addAttribute("bookInfor",bookInfor);
        return "book";
    }

    @RequestMapping(value = "/ShowBookInfor",method = RequestMethod.GET)
    public String showBookInfor(
                                HttpServletRequest request, ModelMap model){

        List<BookEntity> bookInfor=bookRepository.findAll();
        model.addAttribute("bookInfor",bookInfor);
        return "book";
    }

    @RequestMapping(value = "/ShowBookMathsInfor",method = RequestMethod.GET)
    public String ShowBookMathsInfor(HttpServletRequest request, ModelMap model){

        List<BookEntity> bookInfor=bookRepository.findAllByAuthorMajor(Consts.BookMajor.MATH.getValue());
        model.addAttribute("bookInfor",bookInfor);
        return "book-math";
    }

    @RequestMapping(value = "/ShowBookChemistryInfor",method = RequestMethod.GET)
    public String ShowBookChemistryInfor(HttpServletRequest request, ModelMap model){

        List<BookEntity> bookInfor=bookRepository.findAllByAuthorMajor(Consts.BookMajor.CHEMISTRY.getValue());
        model.addAttribute("bookInfor",bookInfor);
        return "book-chemistry";
    }

    @RequestMapping(value = "/ShowBookPhysicsInfor",method = RequestMethod.GET)
    public String ShowBookPhysicsInfor(HttpServletRequest request, ModelMap model){

        List<BookEntity> bookInfor=bookRepository.findAllByAuthorMajor(Consts.BookMajor.PHYSICS.getValue());
        model.addAttribute("bookInfor",bookInfor);
        return "book-chemistry";
    }

    @RequestMapping(value = "/ShowBookCSInfor",method = RequestMethod.GET)
    public String ShowBookCSInfor(HttpServletRequest request, ModelMap model){

        List<BookEntity> bookInfor=bookRepository.findAllByAuthorMajor(Consts.BookMajor.CS.getValue());
        model.addAttribute("bookInfor",bookInfor);
        return "book-computer";
    }

    @RequestMapping(value = "/ShowBookHumanInfor",method = RequestMethod.GET)
    public String ShowBookHumanInfor(HttpServletRequest request, ModelMap model){

        List<BookEntity> bookInfor=bookRepository.findAllByAuthorMajor(Consts.BookMajor.HUMANITIES.getValue());
        model.addAttribute("bookInfor",bookInfor);
        return "book-humanity";
    }

    @RequestMapping(value = "/ShowBookArtInfor",method = RequestMethod.GET)
    public String showBookArtInfor(HttpServletRequest request, ModelMap model){

        List<BookEntity> bookInfor=bookRepository.findAllByAuthorMajor(Consts.BookMajor.ART.getValue());
        model.addAttribute("bookInfor",bookInfor);
        return "book-art";
    }


}
