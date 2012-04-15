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
        
        for (int i=0; i< 20; i++) {
            String messageBody = "Base class for Joda Time based date converters. It contains the logic to parse and format, optionally taking the time zone difference between clients and the server into accoun";
            Message msg = new Message();
            msg.setId(new ObjectId());
            msg.setReceiver(getDefaultUser().getId().toString());
            msg.setSenderEmail("prasanna.tuladhar@gmail.com");
            msg.setSenderFirstname("test-2");
            msg.setSenderPhone("0176215199165");
            msg.setSubject("Message title test....." + i);
            msg.setMessageBody(messageBody);
            msg.setSentTs(new Date());
            
            messageRepository.save(msg);
        }
    }
}
