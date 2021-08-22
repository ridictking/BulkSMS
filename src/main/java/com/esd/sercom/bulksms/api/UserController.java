package com.esd.sercom.bulksms.api;

import com.esd.sercom.bulksms.model.DTO.ChangePasswordDetails;
import com.esd.sercom.bulksms.model.DTO.CreatePassword;
import com.esd.sercom.bulksms.model.DTO.LoginDetails;
import com.esd.sercom.bulksms.model.DTO.UserDetails;
import com.esd.sercom.bulksms.model.entity.AccountManager;
import com.esd.sercom.bulksms.service.usermanagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user/api/v1")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetails> register(@Valid @RequestBody UserDetails details){
        UserDetails userDetails = userService.newUser(details);
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@Valid @RequestBody LoginDetails details){
        UserDetails userDetails = userService.login(details);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<UserDetails> update(@Valid @RequestBody UserDetails details){
        UserDetails userDetails = userService.updateUser(details);
        return new ResponseEntity<>(userDetails, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-password")
    public ResponseEntity<UserDetails> createPassword(@Valid @RequestBody CreatePassword password){
        userService.createPassword(password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<UserDetails> forgotPassword(@RequestParam String email){
        userService.forgotPassword(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/change-password")
    public ResponseEntity<UserDetails> changePassword(@Valid @RequestBody ChangePasswordDetails changePasswordDetails){
        UserDetails userDetails = userService.changePassword(changePasswordDetails);
        return new ResponseEntity<>(userDetails,HttpStatus.CREATED);
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserDetails> getUser( @RequestParam String email){
        UserDetails userDetails = userService.getUser(email);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }

    @PostMapping("/deactivate-user")
    public ResponseEntity<Void> deactivateUser(@RequestParam String email){
        userService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add-accountmanager")
    public ResponseEntity<Void> addAccountManager(@RequestBody AccountManager manager){
        userService.addAccountManager(manager);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update-accountmanager/{accountCode}")
    public ResponseEntity<Void> addAccountManager(@PathVariable String accountCode,  @RequestBody AccountManager manager){
        userService.updateAccountManager(accountCode, manager);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
