package com.groupname.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "book", schema = "root", catalog = "")
public class BookEntity {
    private String isbn;
    private String bookName;
    private String author;
    private Integer authorMajor;
    private String titlePage;
    private String publishingHouse;
    private Integer publishingData;
    private String informationUrl;
    private String contentUrl;
    private Integer number;

    public BookEntity(){
        this.number=0;
    }

    @Id
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn=isbn;
    }

    @Basic
    @Column(name = "bookName")
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName){
        this.bookName=bookName;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author){ this.author=author; }

    @Basic
    @Column(name = "authorMajor")
    public Integer getAuthorMajor() {
        return authorMajor;
    }
    public void setAuthorMajor(Integer authorMajor){
        this.authorMajor=authorMajor;
    }

    @Basic
    @Column(name = "titlePage")
    public String getTitlePage() {
        return titlePage;
    }
    public void setTitlePage(String titlePage){
        this.titlePage=titlePage;
    }

    @Basic
    @Column(name = "publishingHouse")
    public String getPublishingHouse() {
        return publishingHouse;
    }
    public void setPublishingHouse(String publishingHouse){
        this.publishingHouse=publishingHouse;
    }

    @Basic
    @Column(name = "publishingData")
    public Integer getPublishingData() {
        return publishingData;
    }
    public void setPublishingData(Integer publishingData){
        this.publishingData=publishingData;
    }

    @Basic
    @Column(name = "informationUrl")
    public String getInformationUrl() {
        return informationUrl;
    }
    public void setInformationUrl(String informationUrl){
        this.informationUrl=informationUrl;
    }

    @Basic
    @Column(name = "contentUrl",length=1024)
    public String getContentUrl() {
        return contentUrl;
    }
    public void setContentUrl(String contentUrl){
        this.contentUrl=contentUrl;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number){
        this.number=number;
    }


    @Override
    public String toString(){
        return this.isbn+","+
                this.bookName+","+
                this.author+","+
                this.authorMajor+","+
                this.titlePage+","+
                this.publishingHouse+","+
                this.publishingData+","+
                this.informationUrl+","+
                this.contentUrl+" "+
                this.number;

    }
}
