package com.groupname.demo.repository;

import com.groupname.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByUserNo(String userNo);
    ArrayList<UserEntity> findAllByUserStatus(Integer userStatus);
    ArrayList<UserEntity> findAllByUserStatusAndCharacters(Integer userStatus,Integer characters);
}
