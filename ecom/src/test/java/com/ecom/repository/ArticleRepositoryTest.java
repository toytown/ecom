package com.ecom.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.test.AbstractIntegrationTest;

public class ArticleRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    ArticleRepository repository;

    @Before
    public void purgeRepository() {
        repository.deleteAll();
        super.setUp();
    }
    
    @Test
    public void createArticle() throws Exception {

        repository.save(articles);
    }

}
