package com.ecom.service.interfaces;

import com.ecom.domain.User;

public interface UserService {

    public boolean retriveAndSendNewPassword(String userNameOrEmail);
    
    public void save(User user);
}
