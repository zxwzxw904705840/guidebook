package com.groupname.demo.repository;

import com.groupname.demo.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,String> {
    MessageEntity findByMessageNo(String messageNo);
    ArrayList<MessageEntity> findAllByMessageStatus(Integer messageStatus);
}
