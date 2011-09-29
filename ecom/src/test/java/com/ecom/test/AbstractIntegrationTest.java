package com.ecom.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecom.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public abstract class AbstractIntegrationTest {

    static final String COLLECTION = "ecom";

    protected List<Article> articles;
    
    @Autowired
    MongoOperations operations;	
    
    @Before
    public void setUp() {

        operations.dropCollection(COLLECTION);

        articles = new ArrayList<Article>();
        
        Article articleShoe = new Article();
        articleShoe.setTitle("shoe");
        
        Article articleGuitar= new Article();
        articleGuitar.setTitle("guitar");
        
        articles.add(articleShoe);
        articles.add(articleGuitar);
        
    }    
}
