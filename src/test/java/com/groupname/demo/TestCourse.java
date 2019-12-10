package com.groupname.demo;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.CourseEntity;
import com.groupname.demo.entity.MajorEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.service.ManagerOperateService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourse {
    @Autowired
    ManagerOperateService managerOperateService;
    @Test
    public void testClassEntity(){

        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("m10086");
        /*MajorEntity majorEntity = new MajorEntity();
        majorEntity.setMajorNo("66666");*/
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("9787040446081");
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseNo("c00003");
        //courseEntity.setMajor(majorEntity);
        courseEntity.setGuidebook(bookEntity);
        courseEntity.setTerm(2);
        courseEntity.setCourseEnglishName("daxuewulideyingwen");
        courseEntity.setCourseName("大学物理");
        Result result = managerOperateService.addCourse(courseEntity,userEntity);
        System.out.println(result.isSuccess()+result.getMessage());

    }
    @Test
    public void contextLoads(){

    }
}
