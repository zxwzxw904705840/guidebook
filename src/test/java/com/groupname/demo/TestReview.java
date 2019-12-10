package com.groupname.demo;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.BookEntity;
import com.groupname.demo.entity.PurchaseEntity;
import com.groupname.demo.entity.ReviewEntity;
import com.groupname.demo.entity.UserEntity;
import com.groupname.demo.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReview {
    @Autowired
    ReviewRepository reviewRepository;
    @Test
    public void testReviewEntity(){
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewType(Consts.ReviewType.PURCHASE_NO.getValue());
        reviewEntity.setReviewObjectNo("b8bcdb8c9728379c9a0c37095c0a0e4d");
        reviewEntity.resetReviewNo();
        System.out.println(reviewEntity);
        reviewRepository.save(reviewEntity);
    }
    @Test
    public void contextLoads(){

    }
}
