package com.groupname.demo.utils;

public class FileName {
    public static String getFileName(String filePath){
        String[] bookNameList = filePath.split("\\\\");
        return bookNameList[bookNameList.length-1];
    }
}
