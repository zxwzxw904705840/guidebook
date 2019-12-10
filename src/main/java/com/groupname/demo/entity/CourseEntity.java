package com.groupname.demo.entity;

import com.groupname.demo.consts.Consts;

import javax.persistence.*;

@Entity
@Table(name = "course", schema = "root", catalog = "")
public class CourseEntity {
    private String courseNo;
    private String courseName;
    private String courseEnglishName;
    private Integer term;
    private String introduction;
    private BookEntity guidebook;
    private Integer courseType;
    private MajorEntity major;

    public CourseEntity(){
        this.courseType= Consts.CourseType.PUBLIC_OBLIGATORY_COURSE.getValue();
    }
    @Id
    @Column(name = "courseNo")
    public String getCourseNo() {
        return courseNo;
    }
    public void setCourseNo(String courseNo){
        this.courseNo=courseNo;
    }

    @Basic
    @Column(name = "courseName")
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName){
        this.courseName=courseName;
    }

    @Basic
    @Column(name = "courseEnglishName")
    public String getCourseEnglishName() {
        return courseEnglishName;
    }
    public void setCourseEnglishName(String courseEnglishName){
        this.courseEnglishName=courseEnglishName;
    }

    @Basic
    @Column(name = "term")
    public Integer getTerm() {
        return term;
    }
    public void setTerm(Integer term){
        this.term=term;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction){
        this.introduction=introduction;
    }

    @OneToOne
    public BookEntity getGuidebook() {
        return guidebook;
    }
    public void setGuidebook(BookEntity guidebook){
        this.guidebook=guidebook;
    }

    @Basic
    @Column(name = "courseType")
    public Integer getCourseType() {
        return courseType;
    }
    public void setCourseType(Integer courseType){
        this.courseType=courseType;
    }

    @OneToOne
    public MajorEntity getMajor() {
        return major;
    }
    public void setMajor(MajorEntity major){
        this.major=major;
    }

    @Override
    public String toString(){
        return this.courseNo+","+
                this.courseName+","+
                this.courseEnglishName+","+
                this.term+","+
                this.introduction+","+
                ((this.guidebook==null) ? "null" :this.guidebook.getIsbn())+","+
                this.courseType+","+
                this.major;

    }
}
