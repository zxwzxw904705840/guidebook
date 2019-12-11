package com.groupname.demo.repository;

import com.groupname.demo.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    CourseEntity findByCourseNo(String courseNo);
    ArrayList<CourseEntity> findAllByTerm(Integer term);
    ArrayList<CourseEntity> findAll();
    ArrayList<CourseEntity> findAllByCourseNameLike(String courseName);
}
