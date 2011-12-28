package com.ecom.repository;

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
    	user.setPassword("my-passwd");
    	userRepository.save(user);
    }	
}
