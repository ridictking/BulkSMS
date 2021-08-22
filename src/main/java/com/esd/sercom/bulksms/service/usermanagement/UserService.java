package com.esd.sercom.bulksms.service.usermanagement;

import com.esd.sercom.bulksms.model.DTO.ChangePasswordDetails;
import com.esd.sercom.bulksms.model.DTO.CreatePassword;
import com.esd.sercom.bulksms.model.DTO.LoginDetails;
import com.esd.sercom.bulksms.model.DTO.UserDetails;
import com.esd.sercom.bulksms.model.entity.AccountManager;

public interface UserService {
    UserDetails newUser(UserDetails userDetails);
    UserDetails updateUser(UserDetails userDetails);
    UserDetails forgotPassword(String email);
    UserDetails login(LoginDetails login);
    UserDetails changePassword(ChangePasswordDetails userChangePasswordDetails);
    void deleteUser(String uniqueUserId);
    UserDetails getUser(String uniquesUserId);
    void createPassword(CreatePassword password);
    void addAccountManager(AccountManager accountManager);
    void updateAccountManager(String accountCode,AccountManager accountManager);
}
