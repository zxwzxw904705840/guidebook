package com.groupname.demo;


import com.groupname.demo.entity.*;
import com.groupname.demo.service.ManagerOperateService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {
    @Autowired
    ManagerOperateService managerOperateService;

    @Test
    public void testClassEntity(){

        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("t10011");
        UserEntity manager = new UserEntity();
        manager.setUserNo("m10086");
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("9787040446081");
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseNo("c00003");
        ClassEntity classEntity = new ClassEntity();
        classEntity.setTeacher(userEntity);
        classEntity.setCourse(courseEntity);
        classEntity.setGuidebook(bookEntity);
        Result result = managerOperateService.addClass(classEntity,manager);
        System.out.println(result.isSuccess()+result.getMessage());

        //System.out.println(courseRepository.findByCourseNo("c00002").getMajor().getMajorName());

    }
    @Test
    public void contextLoads(){

    }

}
