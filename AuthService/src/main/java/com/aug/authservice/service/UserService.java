package com.aug.authservice.service;

import com.aug.authservice.model.User;
import com.aug.authservice.repository.UserRepository;
import com.aug.authservice.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndPassword(name, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }
}
