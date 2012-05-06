package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public GeoLocation findLocation(String cityOrZip) {
		QGeoLocation geoLocation = new QGeoLocation("geoLocation");
		BooleanBuilder zipCityFinder = new BooleanBuilder();

		if (GeoLocationUtils.isZipCodeOnly(cityOrZip)) {
			zipCityFinder.or(geoLocation.zip.startsWith(cityOrZip));
		} else if (GeoLocationUtils.hasOnlyCityNamePattern(cityOrZip)) {
			zipCityFinder.or(geoLocation.city.startsWith(cityOrZip));
		} else if (GeoLocationUtils.hasZipAndCityNamePattern(cityOrZip)) {
			String zipCity[] = GeoLocationUtils.extractZipOrCity(cityOrZip);
			if (zipCity.length >= 1 && GeoLocationUtils.isZipCodeOnly(zipCity[0])) {
				zipCityFinder.or(geoLocation.zip.startsWith(zipCity[0]));
			}

			if (zipCity.length >= 2 && GeoLocationUtils.isZipCodeOnly(zipCity[1])) {
				zipCityFinder.or(geoLocation.city.startsWith(zipCity[1]));
			}
		} else {
			zipCityFinder.or(geoLocation.city.startsWith(cityOrZip));
		}

		Pageable pageReq = new PageRequest(0, 1);
		List<GeoLocation> geoResults = geoLocationRepository.findAll(zipCityFinder, pageReq).getContent();
		
		if (!geoResults.isEmpty()) {
			return geoResults.get(0);
		}
		
		return null;
	}
	
	@Override
	public Iterable<GeoLocation> findByZipOrCity(String cityOrZip) {
		QGeoLocation geoLocation = new QGeoLocation("geoLocation");
		BooleanBuilder zipCityFinder = new BooleanBuilder();

		if (GeoLocationUtils.isZipCodeOnly(cityOrZip)) {
			zipCityFinder.or(geoLocation.zip.startsWith(cityOrZip));
		} else if (GeoLocationUtils.hasOnlyCityNamePattern(cityOrZip)) {
			zipCityFinder.or(geoLocation.city.startsWith(cityOrZip));
		} else if (GeoLocationUtils.hasZipAndCityNamePattern(cityOrZip)) {
			String zipCity[] = GeoLocationUtils.extractZipOrCity(cityOrZip);
			if (zipCity.length >= 1 && GeoLocationUtils.isZipCodeOnly(zipCity[0])) {
				zipCityFinder.or(geoLocation.zip.startsWith(zipCity[0]));
			}

			if (zipCity.length >= 2 && GeoLocationUtils.isZipCodeOnly(zipCity[1])) {
				zipCityFinder.or(geoLocation.city.startsWith(zipCity[1]));
			}
		} else {
			zipCityFinder.or(geoLocation.city.startsWith(cityOrZip));
		}

		return geoLocationRepository.findAll(zipCityFinder);
	}

	@Override
	public GeoLocation findOne(String id) {
		return geoLocationRepository.findOne(id);
	}


}
