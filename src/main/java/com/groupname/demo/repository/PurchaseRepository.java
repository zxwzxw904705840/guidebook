package com.groupname.demo.repository;

import com.groupname.demo.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity,String> {
    PurchaseEntity findByPurchaseNo(String purchaseNo);
    ArrayList<PurchaseEntity> findAllByPurchaseStatus(Integer purchaseStatus);
    ArrayList<PurchaseEntity> findAll();
}
