package com.esd.sercom.bulksms.dao;

import com.esd.sercom.bulksms.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
