package com.esd.sercom.bulksms.dao;

import com.esd.sercom.bulksms.model.entity.BulkSmsPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulkSmsInvoiceRepo extends JpaRepository<BulkSmsPayment, Long> {
}
