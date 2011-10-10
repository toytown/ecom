package com.ecom.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.domain.RealState;
import com.ecom.test.AbstractIntegrationTest;

public class RealStateRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    RealStateRepository repository;
    
    @Before
    public void purgeRepository() {
        repository.deleteAll();
        super.setUp();
    }
    
    @Test
    public void testSaveRealState() {
        
        
        for (int i=0;i < 50; i++) {
            RealState appartment = new RealState();
            appartment.setTitle(i + " zimmer appartment");            
            appartment.setBedRooms(2 * i -1);
            appartment.setTotalRooms(3);
            appartment.setBalconyAvailable(true);
            appartment.setCategoryId(1);
            appartment.setTypeId(1);            
            appartment.setCost(500 + i* 20);
            appartment.setSize(50.5d);
            realStates.add(appartment);
            
        }
        repository.save(realStates);
        
        assert(repository.count() > 0);
    }
}
