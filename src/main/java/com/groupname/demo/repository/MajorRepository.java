package com.groupname.demo.repository;

import com.groupname.demo.entity.MajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository  extends JpaRepository<MajorEntity,String> {
    MajorEntity findByMajorNo(String userNo);
}
