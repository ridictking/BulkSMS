package com.esd.sercom.bulksms.model.DTO;

import javax.validation.constraints.NotBlank;

public class ChangePasswordDetails {
    @NotBlank
    private String email;
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
