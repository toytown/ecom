package com.ecom.repository;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.domain.Message;
import com.ecom.test.AbstractIntegrationTest;

public class MessageRepositoryTest extends AbstractIntegrationTest{


    @Autowired
    private MessageRepository messageRepository;


    @Before
    public void purgeRepository() {
        messageRepository.deleteAll();
    } 
    
    
    @Test
    public void testCreateMessages() {
        
        for (int i=0; i< 100; i++) {
            String messageBody = "Base class for Joda Time based date converters. It contains the logic to parse and format, optionally taking the time zone difference between clients and the server into accoun";
            Message msg = new Message(new ObjectId(), "1", "prasanna.tuladhar@gmail.com", "Message title test....." + i, messageBody, new Date());
            messageRepository.save(msg);
        }
    }
}
