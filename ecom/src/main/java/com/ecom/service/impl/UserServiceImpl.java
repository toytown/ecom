package com.ecom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.domain.User;
import com.ecom.repository.UserRepository;
import com.ecom.service.interfaces.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public String retrivePassword(String userNameOrEmail, boolean userName) {
        User foundUser = null;
        
        if (userName) {
            foundUser = userRepository.findUserByUserName(userNameOrEmail);
        } else {
            foundUser = userRepository.findUserByUserName(userNameOrEmail);
        }
        
        if (foundUser != null) {
            return foundUser.getPassword();
        } else {
            return null;
        }
        
    }

}
