package com.ecom.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public abstract class AbstractIntegrationTest {

    static final String COLLECTION = "realstate";

    protected List<RealState> realStates = new ArrayList<RealState>();
    
    protected List<RealStateImage> images = new ArrayList<RealStateImage>();
    
    @Autowired
    MongoTemplate mongoTemplate;	
    
    @Before
    public void setUp() {
       //mongoTemplate.dropCollection(COLLECTION);        
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
