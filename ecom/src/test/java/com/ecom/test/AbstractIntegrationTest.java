package com.ecom.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecom.domain.QUser;
import com.ecom.domain.RealState;
import com.ecom.domain.User;
import com.ecom.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public abstract class AbstractIntegrationTest {

	protected static final String COLLECTION = "realstate";

	protected List<RealState> realStates = new ArrayList<RealState>();

	@Autowired
	protected MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	private final String USER_ID ="4efa2c3986f47401f65118fb";
	
	@Before
	public void setUp() {
		User user = getDefaultUser();
		
		if (user == null) {
			User newUser = new User();
			ObjectId userId = new ObjectId(USER_ID);
			newUser.setId(userId);
			newUser.setEmail("prasanna.tuladhar@gmail.com");
			newUser.setCity("München");
			newUser.setZip("80337");
			newUser.setPhone1("49 89 76755263");
			newUser.setFax("prasanna");
			newUser.setUserName("test");
			newUser.setPassword("test");
			userRepository.save(newUser);
		}
	}


	protected User getDefaultUser() {
		QUser user = new QUser("user");
		return userRepository.findOne(user.id.eq(new ObjectId(USER_ID)));
	}
	
	protected File readImageFile(String fileName) throws Exception {
		Resource res = new ClassPathResource(fileName);
		File inputFile = res.getFile();

		if (inputFile.exists()) {
			return inputFile;
		}

		return null;
	}
}
