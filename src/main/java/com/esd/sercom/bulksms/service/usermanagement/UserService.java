package com.esd.sercom.bulksms.service.usermanagement;

import com.esd.sercom.bulksms.model.DTO.ChangePasswordDetails;
import com.esd.sercom.bulksms.model.DTO.CreatePassword;
import com.esd.sercom.bulksms.model.DTO.UserDetails;

public interface UserService {
    UserDetails newUser(UserDetails userDetails);
    UserDetails updateUser(UserDetails userDetails);
    UserDetails forgotPassword(String email);
    UserDetails changePassword(ChangePasswordDetails userChangePasswordDetails);
    void deleteUser(String uniqueUserId);
    UserDetails getUser(String uniquesUserId);
    void createPassword(CreatePassword password);
}
