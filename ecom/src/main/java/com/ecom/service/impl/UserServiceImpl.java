package com.ecom.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.domain.Language;
import com.ecom.domain.MarkedItem;
import com.ecom.domain.User;
import com.ecom.repository.MarkedItemRepository;
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
    
    @Autowired
    private MarkedItemRepository markedItemRepository;
    
    @Override
    public boolean retriveAndSendNewPassword(String userNameOrEmail) {
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
            
            return true;
        }
        
        return false;
        
    }

    @Override
    public void save(User user) {
        if (user != null) {
            userRepository.save(user);
        }
        
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        return userRepository.findUserByUserNameAndPassword(userName, password);
    }

    @Override
    public void saveMarkedItems(String realStateId, String userId) {

            MarkedItem mk = new MarkedItem();
            mk.setId(new ObjectId());
            mk.setItemId(new ObjectId(realStateId));
            mk.setUserId(new ObjectId(userId));
            markedItemRepository.save(mk);
        
    }

}
