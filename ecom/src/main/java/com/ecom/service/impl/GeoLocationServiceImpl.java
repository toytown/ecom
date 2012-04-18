package com.ecom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.common.utils.GeoLocationUtils;
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

        
        if (GeoLocationUtils.isZipCodeOnly(cityOrZipName)) {
        	zipCityFinder.or(geoLocation.zip.startsWith(cityOrZipName));
        } else if (GeoLocationUtils.hasOnlyCityNamePattern(cityOrZipName)) {
        	zipCityFinder.or(geoLocation.city.startsWith(cityOrZipName));
        } else if (GeoLocationUtils.hasZipAndCityNamePattern(cityOrZipName)) {
        	String zipCity[] = GeoLocationUtils.extractZipOrCity(cityOrZipName);
        	if (zipCity.length >= 1 && GeoLocationUtils.isZipCodeOnly(zipCity[0])) {
        		zipCityFinder.or(geoLocation.zip.startsWith(zipCity[0]));
        	} 
        	
        	if (zipCity.length >= 2 && GeoLocationUtils.isZipCodeOnly(zipCity[1])) {
        		zipCityFinder.or(geoLocation.city.startsWith(zipCity[1]));
        	}
        } else {
        	zipCityFinder.or(geoLocation.city.startsWith(cityOrZipName));
        }
        
        return geoLocationRepository.findAll(zipCityFinder);
    }


    
}
