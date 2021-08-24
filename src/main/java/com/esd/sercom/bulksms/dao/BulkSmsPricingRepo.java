package com.esd.sercom.bulksms.dao;

import com.esd.sercom.bulksms.model.entity.BulkSmsPricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BulkSmsPricingRepo extends JpaRepository<BulkSmsPricing,Long> {
    Optional<BulkSmsPricing> findByLowerBoundUnitLessThanEqualAndUpperBoundUnitGreaterThanEqual(long unit, long unit2);
    Optional<BulkSmsPricing> findByRank(String rank);
}
