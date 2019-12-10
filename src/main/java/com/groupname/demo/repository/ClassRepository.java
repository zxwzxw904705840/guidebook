package com.groupname.demo.repository;

import com.groupname.demo.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity,String> {
    ClassEntity findByClassNo(String classNo);
}
