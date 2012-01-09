package com.ecom.repository;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.domain.User;
import com.ecom.test.AbstractIntegrationTest;

public class UserRepositoryTest extends AbstractIntegrationTest {

	@Autowired
	private UserRepository userRepository;
	
	
    @Before
    public void purgeRepository() {
   	 userRepository.deleteAll();

    }
    
    @Test
    public void createUser() throws Exception {

    	User user = new User();
    	user.setEmail("prasanna.tuladhar@gmail.com");
    	user.setCity("München");
    	user.setZip("80337");
    	user.setPhone1("49 89 76755263");
    	user.setFax("prasanna");
    	user.setUserName("my-user");
    	user.setFirstName("Prasanna");
    	user.setLastName("Tuladhar");
    	user.setInsertTs(new Date());
    	user.setPassword("my-passwd");
    	user.setUserType(0);
    	userRepository.save(user);
    }	
}
