package com.groupname.demo.entity;

import com.groupname.demo.utils.MD5;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message", schema = "root", catalog = "")
public class MessageEntity {
    private String messageNo;
    private UserEntity user;
    private String messageContent;
    private Integer messageStatus;

    public MessageEntity(){
        messageNo= MD5.getMD5(String.valueOf(new Date().getTime()));
    }
    @Id
    @Column(name = "messageNo")
    public String getMessageNo() {
        return messageNo;
    }
    private void setMessageNo(String messageNo){this.messageNo=messageNo;}
    public void resetMessageNo(){
        if(user!=null)
            this.setMessageNo(MD5.getMD5(String.valueOf(new Date().getTime())+user.getUserNo()));
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user){
        this.user=user;
    }

    @Basic
    @Column(name = "messageContent")
    public String getMessageContent() {
        return messageContent;
    }
    public void setMessageContent(String messageContent){
        this.messageContent=messageContent;
    }

    @Basic
    @Column(name = "messageStatus")
    public Integer getMessageStatus() {
        return messageStatus;
    }
    public void setMessageStatus(Integer messageStatus){
        this.messageStatus=messageStatus;
    }

    @Override
    public String toString(){
        return this.messageNo+","+
                (this.user==null?"null":this.user.getUserName())+","+
                this.messageContent+","+
                this.messageStatus;

    }
}
