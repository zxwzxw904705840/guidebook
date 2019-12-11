package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.PurchaseEntity;
import com.groupname.demo.entity.ReviewEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.BookRepository;
import com.groupname.demo.repository.PurchaseRepository;
import com.groupname.demo.repository.ReviewRepository;
import com.groupname.demo.repository.UserRepository;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    PurchaseRepository purchaseRepository;

    public Result purchaseBook(PurchaseEntity purchaseEntity){
        Result result = checkPurchase(purchaseEntity);
        if(!result.isSuccess()){
            return result;
        }
        purchaseEntity.resetPurchaseNo();
        reviewRepository.save(new ReviewEntity(Consts.ReviewType.PURCHASE_NO.getValue(),purchaseEntity.getPurchaseNo()));
        purchaseRepository.save(purchaseEntity);
        return new Result(true, Consts.PURCHASE_REQUEST_SUCCESS);
    }
    private Result checkPurchase(PurchaseEntity purchaseEntity){
        if(purchaseEntity==null){
            return new Result(false,Consts.PURCHASE_FAILED);
        }
        if(purchaseEntity.getNumber()==null||purchaseEntity.getNumber()<0){
            return new Result(false,Consts.PURCHASE_NUMBER_ERROR);
        }
        if(purchaseEntity.getPurchaser()==null||purchaseEntity.getPurchaser().getUserNo()==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(purchaseEntity.getBook()==null||purchaseEntity.getBook().getIsbn()==null||purchaseEntity.getBook().getIsbn().equals("")){
            return new Result(false,Consts.ISBN_IS_EMPTY);
        }
        if(purchaseEntity.getBook().getIsbn().length()!=13){
            return new Result(false,Consts.ISBN_ERROR);
        }
        if(bookRepository.findByIsbn(purchaseEntity.getBook().getIsbn())==null){
            return new Result(false,Consts.GUIDEBOOK_NOT_EXISTS_ERROR);
        }
        UserEntity teacher =userRepository.findByUserNo(purchaseEntity.getPurchaser().getUserNo());
        if(teacher==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(teacher.getCharacters()!=Consts.UserType.TEACHER.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        if(teacher.getUserStatus()!=Consts.Status.NORMAL.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        return new Result(true,"");
    }
}
