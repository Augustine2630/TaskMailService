package com.aug.authservice.controller;

import com.aug.authservice.model.User;
import com.aug.authservice.service.UserService;
import com.aug.authservice.util.JwtGenerator;
import com.aug.authservice.util.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final JwtGenerator jwtGenerator;

    public UserController(UserService userService, JwtGenerator jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUsername() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUsername(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
