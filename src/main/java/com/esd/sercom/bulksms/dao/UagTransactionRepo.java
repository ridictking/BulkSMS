package com.esd.sercom.bulksms.dao;

import com.esd.sercom.bulksms.model.entity.UagTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UagTransactionRepo extends JpaRepository<UagTransactionEntity,Long> {
    List<UagTransactionEntity> findByAccountNameOrderByIdDesc(String accountName);
}
