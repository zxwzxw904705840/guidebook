package com.groupname.demo;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.BookRepository;
import com.groupname.demo.service.ManagerOperateService;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("9787040446081");
        bookEntity.setPublishingHouse("高等教育出版社");
        bookEntity.setPublishingData(2019);
        bookEntity.setNumber(2147483647);
        bookEntity.setBookName("大学物理");
        bookEntity.setAuthor("宋更新");
        bookEntity.setAuthorMajor(Consts.BookMajor.PHYSICS.getValue());
        Result result = managerOperateService.addBook(bookEntity,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());
        //System.out.println(bookRepository.findAll());
    }
    @Test
    public void contextLoads(){

    }
}
