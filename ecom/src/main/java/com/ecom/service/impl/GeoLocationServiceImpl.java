package com.ecom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.domain.GeoLocation;
import com.ecom.domain.QGeoLocation;
import com.ecom.repository.GeoLocationRepository;
import com.ecom.service.interfaces.GeoLocationService;
import com.mysema.query.BooleanBuilder;

@Service("geoLocationService")
public class GeoLocationServiceImpl implements GeoLocationService {

    @Autowired
    private GeoLocationRepository geoLocationRepository;
    
    @Override
    public Iterable<GeoLocation> findByZipOrCity(String cityOrZip) {
        QGeoLocation geoLocation = new QGeoLocation("geoLocation");
        BooleanBuilder zipCityFinder = new BooleanBuilder();
        zipCityFinder.or(geoLocation.zip.startsWith(cityOrZip));
        zipCityFinder.or(geoLocation.city.startsWith(cityOrZip));        
        return geoLocationRepository.findAll(zipCityFinder);
    }


}
