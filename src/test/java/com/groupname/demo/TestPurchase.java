package com.groupname.demo;

import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.PurchaseEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.PurchaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPurchase {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Test
    public void testPurchaseEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("t10011");
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("9787040519457");
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setBook(bookEntity);
        purchaseEntity.setPurchaser(userEntity);
        purchaseEntity.setNumber(65535);
        System.out.println(purchaseEntity);
        purchaseRepository.save(purchaseEntity);
    }
    @Test
    public void contextLoads(){

    }
}
