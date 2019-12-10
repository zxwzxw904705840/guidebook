package com.groupname.demo.entity;

import com.groupname.demo.utils.MD5;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review", schema = "root", catalog = "")
public class ReviewEntity {
    private String reviewNo;
    private Integer reviewType;
    private String reviewObjectNo;
    private UserEntity reviewer;

    public ReviewEntity(){
        reviewNo= MD5.getMD5(String.valueOf(new Date().getTime()));
    }

    @Id
    @Column(name = "reviewNo")
    public String getReviewNo() {
        return reviewNo;
    }
    private void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
    }
    public void resetReviewNo(){
        if(reviewObjectNo!=null)
            this.setReviewNo(MD5.getMD5(String.valueOf(new Date().getTime())+reviewObjectNo));
    }

    @Basic
    @Column(name = "reviewType")
    public Integer getReviewType(){return reviewType;}
    public void setReviewType(Integer reviewType){
        this.reviewType=reviewType;
    }

    @Basic
    @Column(name = "reviewObjectNo")
    public String getReviewObjectNo(){return reviewObjectNo;}
    public void setReviewObjectNo(String reviewObjectNo){
        this.reviewObjectNo=reviewObjectNo;
    }

    @OneToOne
    public UserEntity getReviewer(){return reviewer;}
    public void setReviewer(UserEntity reviewer){
        this.reviewer=reviewer;
    }

    @Override
    public String toString(){
        return this.reviewNo+","+
                this.reviewType+","+
                this.reviewObjectNo+","+
                (this.reviewer==null?"null":this.reviewer.getUserName());

    }
}
