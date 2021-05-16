package com.esd.sercom.bulksms.model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginDetails {
    @NotBlank
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).{8,}$", message = "Password must be at least in 8 characters, at one A-Z a-z 1-9 special xters")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
