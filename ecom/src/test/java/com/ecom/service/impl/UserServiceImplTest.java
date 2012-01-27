package com.ecom.service.impl;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.domain.User;
import com.ecom.service.interfaces.UserService;
import com.ecom.test.AbstractIntegrationTest;
import com.ecom.web.utils.CryptoUtils;

public class UserServiceImplTest extends AbstractIntegrationTest{

    @Autowired
    private UserService userService;
    
    @Test
    public void testRetriveAndSendNewPassword() {
        
        User user = new User();
        user.setEmail("prasanna.tuladhar@gmail.com");
        user.setCity("München");
        user.setZip("80337");
        user.setPhone1("49 89 76755263");
        user.setFax("prasanna");
        user.setUserName("testUserRetrieve");
        user.setFirstName("Prasanna");
        user.setLastName("Tuladhar");
        user.setInsertTs(new Date());
        user.setPassword(CryptoUtils.encrypt("test-user-retrieve-passwd"));
        user.setUserType(1);
        userService.save(user);
        
        userService.retriveAndSendNewPassword("testUserRetrieve");
    }

    @Test
    public void testSaveUser() {
        fail("Not yet implemented");
    }

}
