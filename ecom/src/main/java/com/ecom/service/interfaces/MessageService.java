package com.ecom.service.interfaces;

import org.bson.types.ObjectId;

import com.ecom.domain.Message;

public interface MessageService {

    public void sendMessage(Message message);
    
    public void deleteMessage(ObjectId messageId);
}
