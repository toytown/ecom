package com.ecom.service.impl;

import org.apache.commons.lang3.StringUtils;
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
    public Iterable<GeoLocation> findByZipOrCity(String cityOrZipName) {
        QGeoLocation geoLocation = new QGeoLocation("geoLocation");
        BooleanBuilder zipCityFinder = new BooleanBuilder();
        
        if (StringUtils.isNotEmpty(cityOrZipName)) {
        	zipCityFinder.or(geoLocation.zip.startsWith(cityOrZipName));
        }
        
        if (StringUtils.isNotEmpty(cityOrZipName)) {
        	zipCityFinder.or(geoLocation.city.startsWith(cityOrZipName));
        }
        
        return geoLocationRepository.findAll(zipCityFinder);
    }


    
}
