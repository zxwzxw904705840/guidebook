package com.groupname.demo;

import com.groupname.demo.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBook {
    @Autowired
    BookRepository bookRepository;
    @Test
    public void testBookRepository(){
        System.out.println(bookRepository.findAll());
    }
    @Test
    public void contextLoads(){

    }
}
