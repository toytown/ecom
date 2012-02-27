package com.ecom.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.domain.GeoLocation;
import com.ecom.service.interfaces.GeoLocationService;
import com.ecom.test.AbstractIntegrationTest;

public class GeoLocationServiceTest extends AbstractIntegrationTest {

    @Autowired
    private GeoLocationService geoLocationService;
    
    @Test
    public void testFindByZipOrCity() {
        Iterator<GeoLocation> locations = geoLocationService.findByZipOrCity("81669").iterator();
        assertNotNull(locations);
        while (locations.hasNext()) {
            GeoLocation loc = locations.next();
            assertNotNull(loc.getCountry());
            assertTrue(loc.getCity().equalsIgnoreCase("München"));
        }
    }

    
    @Test
    public void testFindByZipOrCity2() {
        Iterator<GeoLocation> locations = geoLocationService.findByZipOrCity("München").iterator();
        assertNotNull(locations);
        boolean found = false;
        while (locations.hasNext()) {
            GeoLocation loc = locations.next();
            assertNotNull(loc.getCountry());
            found = true;
        }
        
        assertTrue(found);
    }
    
}
