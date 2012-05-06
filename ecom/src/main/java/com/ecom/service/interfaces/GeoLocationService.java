package com.ecom.service.interfaces;

import com.ecom.domain.GeoLocation;

public interface GeoLocationService {

    public Iterable<GeoLocation> findByZipOrCity(String cityOrZip);

    public GeoLocation findLocation(String cityOrZip);
    
    public GeoLocation findOne(String id);
    
}
