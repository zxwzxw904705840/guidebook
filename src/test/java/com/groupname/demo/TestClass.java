package com.groupname.demo;

import com.groupname.demo.controller.LoginController;
import com.groupname.demo.entity.*;
import com.groupname.demo.repository.*;
import com.groupname.demo.utils.MD5;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;

import javax.servlet.http.PushBuilder;
import java.awt.print.Book;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {
    @Autowired
    LoginController loginController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ClassRepository classRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void testClassEntity(){

        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("t10011");
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseNo("c00002");
        ClassEntity classEntity = new ClassEntity();
        classEntity.setTeacher(userEntity);
        classEntity.setCourse(courseEntity);

        System.out.println(classEntity);
        classRepository.save(classEntity);

        //System.out.println(courseRepository.findByCourseNo("c00002").getMajor().getMajorName());

    }
    @Test
    public void contextLoads(){

    }

}
