package com.groupname.demo.entity;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.utils.MD5;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file", schema = "root", catalog = "")
public class FileEntity {
    private String fileNo;
    private ClassEntity classEntity;
    private String filePath;
    private Integer fileStatus;
    public FileEntity(){
        this.fileNo= MD5.getMD5(String.valueOf(new Date().getTime()));
        this.fileStatus= Consts.Status.REVIEWING.getValue();
    }

    @Id
    @Column(name = "fileNo")
    public String getFileNo() {
        return fileNo;
    }
    public void setFileNo(String fileNo){
        this.fileNo=fileNo;
    }
    public void resetFileNo(){
        if(fileNo!=null)
            this.setFileNo(MD5.getMD5(String.valueOf(new Date().getTime())+filePath));
    }

    @ManyToOne
    public ClassEntity getClassEntity() {
        return classEntity;
    }
    public void setClassEntity(ClassEntity classEntity){
        this.classEntity=classEntity;
    }

    @Basic
    @Column(name = "filePath")
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath){
        this.filePath=filePath;
    }

    @Basic
    @Column(name = "fileStatus")
    public Integer getFileStatus() {
        return fileStatus;
    }
    public void setFileStatus(Integer fileStatus){
        this.fileStatus=fileStatus;
    }
}
