package com.groupname.demo.utils;

public class BookName {
    public static String getBookEnglishName(String bookName){
        String[] bookNameList = bookName.split("\\|");
        return bookNameList[0];
    }
    public static String getBookName(String bookName){
        String[] bookNameList = bookName.split("\\|");
        return bookNameList[bookNameList.length-1];
    }
}
