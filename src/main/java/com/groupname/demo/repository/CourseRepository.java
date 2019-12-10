package com.groupname.demo.repository;

import com.groupname.demo.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    CourseEntity findByCourseNo(String courseNo);
}
