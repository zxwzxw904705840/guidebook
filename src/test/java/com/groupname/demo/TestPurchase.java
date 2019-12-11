package com.groupname.demo;

import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.PurchaseEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.PurchaseRepository;
import com.groupname.demo.service.PurchaseService;
import com.groupname.demo.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPurchase {
    @Autowired
    PurchaseService purchaseService;
    @Test
    public void testPurchaseEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("t10011");
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("9787040446081");
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setNumber(65535);
        purchaseEntity.setPurchaser(userEntity);
        purchaseEntity.setBook(bookEntity);
        Result result = purchaseService.purchaseBook(purchaseEntity);
        System.out.println(result.isSuccess()+result.getMessage());
    }
    @Test
    public void contextLoads(){

    }
}
