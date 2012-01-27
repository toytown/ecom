package com.ecom.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.domain.Language;
import com.ecom.domain.User;
import com.ecom.repository.UserRepository;
import com.ecom.service.interfaces.EmailService;
import com.ecom.service.interfaces.UserService;
import com.ecom.web.utils.CryptoUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Override
    public void retriveAndSendNewPassword(String userNameOrEmail) {
        User foundUser = null;
        boolean isEmail = false;
        
        if (emailService.validateEmail(userNameOrEmail)) {
            isEmail = true;
        }
        
        if (isEmail) {
            foundUser = userRepository.findUserByEmail(userNameOrEmail);
        } else {
            foundUser = userRepository.findUserByUserName(userNameOrEmail);
        }
        
        if (foundUser != null) {
            
            Map<String, Object> paramsBody = new HashMap<String, Object>();
            String newPassword = UUID.randomUUID().toString();
            foundUser.setPassword(CryptoUtils.encrypt(newPassword));
            paramsBody.put("newPassword", newPassword);
            
            String subject = emailService.getEmailHeaderTemplate(Language.EN, "activation_email", new Object[] { foundUser.getUserName() });
            String body = emailService.getEmailBodyTemplate(Language.EN, "activation_email", new Object[] { newPassword });
            
            emailService.sendEmail("prasanna.tuladhar@gmail.com", "support@ecom.com", subject, body);
        }
        
    }

    @Override
    public void save(User user) {
        if (user != null) {
            userRepository.save(user);
        }
        
    }

}
