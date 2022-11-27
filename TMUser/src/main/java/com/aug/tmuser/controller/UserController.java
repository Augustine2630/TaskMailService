package com.aug.tmuser.controller;

import com.aug.tmuser.model.User;
import com.aug.tmuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public Optional<User> getUserById(@RequestParam("id") Integer id){
        if (userService.getUserById(id).isEmpty()){
            System.out.println("No user");
        }
        return userService.getUserById(id);
    }

    @GetMapping("/u")
    public String user(){
        return "user";
    }
}
