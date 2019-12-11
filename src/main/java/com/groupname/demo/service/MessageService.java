package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.MessageEntity;
import com.groupname.demo.entity.ReviewEntity;
import com.groupname.demo.repository.MessageRepository;
import com.groupname.demo.repository.ReviewRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ReviewRepository reviewRepository;
    public Result addMessage(MessageEntity messageEntity){
        Result result = checkMessage(messageEntity);
        if(!result.isSuccess()){
            return result;
        }
        messageEntity.resetMessageNo();
        messageRepository.save(messageEntity);
        reviewRepository.save(new ReviewEntity(Consts.ReviewType.MESSAGE_NO.getValue(),messageEntity.getMessageNo()));
        return new Result(true, Consts.ADD_MESSAGE_SUCCESS);
    }
    private Result checkMessage(MessageEntity messageEntity){
        if(messageEntity==null){
            return new Result(false,Consts.ADD_MESSAGE_FAILED);
        }
        if(messageEntity.getMessageContent()==null||messageEntity.getMessageContent().equals("")){
            return new Result(false,Consts.MESSAGE_CONTENT_IS_EMPTY);
        }
        if(messageEntity.getUser()==null||messageEntity.getUser().getUserNo()==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(userRepository.findByUserNo(messageEntity.getUser().getUserNo())==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        return new Result(true,"");
    }
}
