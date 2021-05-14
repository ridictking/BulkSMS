package com.esd.sercom.bulksms.api;

import com.esd.sercom.bulksms.model.DTO.UserDetails;
import com.esd.sercom.bulksms.service.usermanagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetails> register(@RequestBody UserDetails details){
        UserDetails userDetails = userService.newUser(details);
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
    }
}
