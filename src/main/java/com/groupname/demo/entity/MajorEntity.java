package com.groupname.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "major", schema = "root", catalog = "")
public class MajorEntity {
    private String majorNo;
    private String majorName;


    @Id
    @Column(name = "majorNo")
    public String getMajorNo() {
        return majorNo;
    }
    public void setMajorNo(String majorNo){
        this.majorNo=majorNo;
    }

    @Basic
    @Column(name = "majorName")
    public String getMajorName() {
        return majorName;
    }
    public void setMajorName(String majorName){
        this.majorName=majorName;
    }

    @Override
    public String toString(){
        return this.majorName;

    }





}
