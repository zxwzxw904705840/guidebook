package com.groupname.demo.entity;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.utils.MD5;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase", schema = "root", catalog = "")
public class PurchaseEntity {
    private String purchaseNo;
    private UserEntity purchaser;
    private BookEntity book;
    private Integer number;
    private Integer purchaseStatus;

    public PurchaseEntity(){
        purchaseNo= MD5.getMD5(String.valueOf(new Date().getTime()));
        this.purchaseStatus= Consts.PurchaseStatus.REVIEWING.getValue();
    }

    @Id
    @Column(name = "purchaseNo")
    public String getPurchaseNo() {
        return purchaseNo;
    }
    private void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }
    public void resetPurchaseNo(){
        if(purchaser!=null)
            this.setPurchaseNo(MD5.getMD5(String.valueOf(new Date().getTime())+purchaser.getUserNo()));
    }

    @ManyToOne
    public UserEntity getPurchaser(){return purchaser;}
    public void setPurchaser(UserEntity purchaser){this.purchaser=purchaser;}

    @ManyToOne
    public BookEntity getBook(){return book;}
    public void setBook(BookEntity book){this.book=book;}

    @Basic
    @Column(name = "number")
    public Integer getNumber(){return number;}
    public void setNumber(Integer number){
        this.number=number;
    }

    @Basic
    @Column(name = "purchaseStatus")
    public Integer getPurchaseStatus(){return purchaseStatus;}
    public void setPurchaseStatus(Integer purchaseStatus){
        this.purchaseStatus=purchaseStatus;
    }

    @Override
    public String toString(){
        return this.purchaseNo+","+
                (this.purchaser==null?"null":this.purchaser.getUserName())+","+
                (this.book==null?"null":this.book.getBookName())+","+
                this.getNumber()+","+
                this.getPurchaseStatus();

    }
}
