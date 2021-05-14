package com.esd.sercom.bulksms.api;

import com.esd.sercom.bulksms.model.DTO.CreatePassword;
import com.esd.sercom.bulksms.model.DTO.UserDetails;
import com.esd.sercom.bulksms.service.usermanagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
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

    @PostMapping("/update")
    public ResponseEntity<UserDetails> update(@Valid @RequestBody UserDetails details){
        UserDetails userDetails = userService.newUser(details);
        return new ResponseEntity<>(userDetails, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/create-password")
    public ResponseEntity<UserDetails> createPassword(@RequestBody CreatePassword password){
        userService.createPassword(password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
