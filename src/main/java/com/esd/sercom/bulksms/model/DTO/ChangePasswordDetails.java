package com.esd.sercom.bulksms.model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ChangePasswordDetails {
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$", message = "Password must be at least in 8 characters, at one A-Z a-z 1-9 special xters")
    private String oldPassword;
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$", message = "Password must be at least in 8 characters, at one A-Z a-z 1-9 special xters")
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
