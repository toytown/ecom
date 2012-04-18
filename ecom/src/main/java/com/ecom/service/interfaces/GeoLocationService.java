package com.ecom.service.interfaces;

import com.ecom.domain.GeoLocation;

public interface GeoLocationService {

    public Iterable<GeoLocation> findByZipOrCity(String cityOrZipName);

}
