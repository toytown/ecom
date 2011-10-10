package com.ecom.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecom.domain.RealState;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public abstract class AbstractIntegrationTest {

    static final String COLLECTION = "realstate";

    protected List<RealState> realStates = new ArrayList<RealState>();
    
    @Autowired
    MongoTemplate mongoTemplate;	
    
    @Before
    public void setUp() {
        mongoTemplate.dropCollection(COLLECTION);        
    }    
    

}
