package com.groupname.demo.repository;

import com.groupname.demo.entity.ClassEntity;
import com.groupname.demo.entity.CourseEntity;
import com.groupname.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity,String> {
    ClassEntity findByClassNo(String classNo);
    ArrayList<ClassEntity> findAllByGuidebookStatus(Integer guidebookStatus);
    ArrayList<ClassEntity> findAll();
    ArrayList<ClassEntity> findAllByTeacher(UserEntity teacher);
    ArrayList<ClassEntity> findAllByCourse(CourseEntity courseEntity);
}
