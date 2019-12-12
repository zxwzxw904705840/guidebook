package com.groupname.demo.repository;

import com.groupname.demo.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,String> {
    FileEntity findByFileNo(String fileNo);
    ArrayList<FileEntity> findAllByFileStatus(Integer fileStatus);
}
