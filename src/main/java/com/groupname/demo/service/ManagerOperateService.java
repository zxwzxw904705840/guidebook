package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.BookRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerOperateService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    /*
    添加图书
     */
    public Result addBook(BookEntity book, UserEntity user){
        Result result = checkUserPermission(user);
        if(!result.isSuccess()){
            return result;
        }
        result = checkBook(book);
        if(!result.isSuccess()){
            return result;
        }
        bookRepository.save(book);
        return new Result(true, Consts.ADD_BOOK_SUCCESS);
    }
     private Result checkUserPermission(UserEntity user){
        if(user==null) {
            return new Result(false, Consts.PERMISSION_DENIED);
        }
        UserEntity userEntity = userRepository.findByUserNo(user.getUserNo());
        if(userEntity==null||userEntity.getCharacters()!=Consts.UserType.MANAGER.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        return new Result(true, Consts.ACCOUNT_CAN_USE);
     }
     private Result checkBook(BookEntity book){
         if(book==null){
             return new Result(false,Consts.ADD_BOOK_FAILED);
         }
         if(book.getIsbn()==null||book.getIsbn().equals("")){
             return new Result(false,Consts.ISBN_IS_EMPTY);
         }
         if(book.getIsbn().length()!=13){
             return new Result(false,Consts.ISBN_ERROR);
         }
         if(book.getBookName()==null||book.getBookName().equals("")){
             return new Result(false,Consts.BOOK_NAME_IS_EMPTY);
         }
         if(book.getPublishingHouse()==null||book.getPublishingHouse().equals("")){
             return new Result(false,Consts.PUBLISHING_HOUSE_IS_EMPTY);
         }
         if(book.getPublishingData()==null){
             return new Result(false,Consts.PUBLISHING_TIME_IS_EMPTY);
         }
         if(book.getAuthor()==null||book.getAuthor().equals("")){
             return new Result(false,Consts.AUTHOR_IS_EMPTY);
         }
         if(book.getAuthorMajor()==null){
             return new Result(false,Consts.AUTHOR_MAJOR_IS_EMPTY);
         }
         if(book.getAuthorMajor()<0||book.getAuthorMajor()>=Consts.BookMajor.values().length){
             return new Result(false,Consts.AUTHOR_MAJOR_ERROR);
         }
         if(bookRepository.findByIsbn(book.getIsbn())!=null){
             return new Result(false,Consts.BOOK_EXISTS);
         }
         return new Result(true,"");
     }
}
