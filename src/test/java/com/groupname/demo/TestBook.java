package com.groupname.demo;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.BookRepository;
import com.groupname.demo.service.ManagerOperateService;
import com.groupname.demo.utils.BookName;
import com.groupname.demo.utils.FileName;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBook {
    @Autowired
    ManagerOperateService managerOperateService;
    @Test
    public void testBookRepository(){
        String bookName ="123|a456";
       // System.out.println(BookName.getBookName(bookName));
        String titleName=BookName.getBookName(bookName);
        System.out.println(titleName.charAt(0));
        System.out.println(BookName.getBookEnglishName(bookName));
        bookName ="123";
        System.out.println(BookName.getBookName(bookName));
        System.out.println(BookName.getBookEnglishName(bookName));
        String filePath="123\\456\\789";
        System.out.println(FileName.getFileName(filePath));
    }
    @Test
    public void contextLoads(){

    }
}
