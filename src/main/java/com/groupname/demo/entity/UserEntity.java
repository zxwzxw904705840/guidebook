package com.groupname.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "root", catalog = "")
public class UserEntity implements Serializable {


    private String userNo;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private Integer characters;
    private String remark;
    private String majorNo;
    private Integer userStatus;

    @Id
    @Column(name = "userno")
    public String getUserNo() {
        return userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "username")
    public String getUserName(){return userName;}
    public void setUserName(String userName){
        this.userName=userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "characters")
    public Integer getCharacters() {
        return characters;
    }
    public void setCharacters(Integer characters) {
        this.characters = characters;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Basic
    @Column(name = "majorNo")
    public String getMajorNo() {
        return majorNo;
    }
    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    @Basic
    @Column(name = "userStatus")
    public Integer getUserStatus() {
        return userStatus;
    }
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString(){
        return this.userNo+","+
                this.userName+","+
                this.password+","+
                this.phone+","+
                this.email+","+
                this.characters+","+
                this.remark+","+
                this.majorNo+","+
                this.userStatus;

    }
}
