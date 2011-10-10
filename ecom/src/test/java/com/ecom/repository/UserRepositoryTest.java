package com.ecom.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.domain.User;
import com.ecom.test.AbstractIntegrationTest;

public class UserRepositoryTest extends AbstractIntegrationTest {

	@Autowired
	UserRepository userRepo;
	
	
    @Before
    public void purgeRepository() {
    	userRepo.deleteAll();
        super.setUp();
    }
    
    @Test
    public void createUser() throws Exception {

    	User user = new User();
    	user.setFax("prasanna");
    	userRepo.save(user);
    }	
}
