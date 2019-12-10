package com.groupname.demo.repository;

import com.groupname.demo.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity,String> {
    PurchaseEntity findByPurchaseNo(String purchaseNo);
}
