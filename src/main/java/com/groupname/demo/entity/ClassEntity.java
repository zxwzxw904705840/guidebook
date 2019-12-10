package com.groupname.demo.entity;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.utils.MD5;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "class", schema = "root", catalog = "")
public class ClassEntity {
    private String classNo;
    private CourseEntity course;
    private UserEntity teacher;
    private BookEntity guidebook;
    private Integer guidebookStatus;

    public ClassEntity(){
        classNo= MD5.getMD5(String.valueOf(new Date().getTime()));
        this.guidebookStatus= Consts.Status.NORMAL.getValue();
    }

    @Id
    @Column(name = "classNo")
    public String getClassNo() {
        return classNo;
    }
    private void setClassNo(String classNo){
        this.classNo=classNo;
    }
    public void resetClassNo(){
        if(course!=null)
            this.setClassNo(MD5.getMD5(String.valueOf(new Date().getTime())+course.getCourseNo()));
    }

    @OneToOne
    public CourseEntity getCourse() {
        return course;
    }
    public void setCourse(CourseEntity course){
        this.course=course;
    }

    @ManyToOne
    public UserEntity getTeacher() {
        return teacher;
    }
    public void setTeacher(UserEntity teacher){
        this.teacher=teacher;
    }

    @ManyToOne
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
