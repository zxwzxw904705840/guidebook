package com.groupname.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "message", schema = "root", catalog = "")
public class MessageEntity {
    private Integer messageNo;
    private UserEntity user;
    private String messageContent;
    private Integer messageStatus;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "messageNo")
    public Integer getMessageNo() {
        return messageNo;
    }
    public void setMessageNo(Integer messageNo){
        this.messageNo=messageNo;
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
