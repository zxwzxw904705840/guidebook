package com.groupname.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "class", schema = "root", catalog = "")
public class ClassEntity {
    private String classNo;
    private CourseEntity course;
    private UserEntity teacher;
    private BookEntity guidebook;
    private Integer guidebookStatus;

    @Id
    @Column(name = "classNo")
    public String getClassNo() {
        return classNo;
    }
    public void setClassNo(String classNo){
        this.classNo=classNo;
    }

    @OneToOne
    public CourseEntity getCourse() {
        return course;
    }
    public void setCourse(CourseEntity course){
        this.course=course;
    }

    @OneToOne
    public UserEntity getTeacher() {
        return teacher;
    }
    public void setTeacher(UserEntity teacher){
        this.teacher=teacher;
    }

    @OneToOne
    public BookEntity getGuidebook() {
        return guidebook;
    }
    public void setGuidebook(BookEntity guidebook){
        this.guidebook=guidebook;
    }

    @Basic
    @Column(name = "guidebookStatus")
    public Integer getGuidebookStatus() {
        return guidebookStatus;
    }
    public void setGuidebookStatus(Integer guidebookStatus){
        this.guidebookStatus=guidebookStatus;
    }

    @Override
    public String toString(){
        return this.classNo+","+
                (this.course==null?"null":this.course.getCourseName())+","+
                (this.teacher==null?"null":this.teacher.getUserName())+","+
                (this.guidebook==null ? "null" :this.guidebook.getIsbn())+","+
                this.guidebookStatus;

    }
}
