package com.esd.sercom.bulksms.dao;

import com.esd.sercom.bulksms.model.entity.AccountManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountManagerRepo extends JpaRepository<AccountManager, Long> {
    Optional<AccountManager> findByAccountCode(String email);
}
