package com.ecom.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class GeoLocationUtils {

	private static final String SPLIT_EXPR = "[,\\s.]+";
	private static final String CITY_NAME_EXPR = "(^[a-zA-ZäüößÄÜÖß\\s\\.\\,\\-]+)";
	private static final String ZIP_CITY_NAME_EXPR = "(^[\\da-zA-ZäüößÄÜÖß\\s\\.\\,\\-]+)";
	private static final Pattern CITY_NAME_PATTERN = Pattern.compile(CITY_NAME_EXPR);
	private static final Pattern ZIP_CITY_NAME_PATTERN = Pattern.compile(ZIP_CITY_NAME_EXPR);
	
	public static boolean isZipCodeOnly(String value) {
		return StringUtils.isNotEmpty(value) && StringUtils.isNumeric(value.trim());
	}

	public static boolean hasOnlyCityNamePattern(String value) {
		if (StringUtils.isNotEmpty(value)) {
			Matcher matcher = CITY_NAME_PATTERN.matcher(value.trim());
			return matcher.matches();
		} 
		
		return false;
	}

	public static boolean hasZipAndCityNamePattern(String value) {
		if (StringUtils.isNotEmpty(value)) {
			Matcher matcher = ZIP_CITY_NAME_PATTERN.matcher(value.trim());
			return matcher.matches();
		} 
		
		return false;
	}
	
	public static boolean isBothZipAndCity(String value) {
		return StringUtils.isNotEmpty(value) && hasZipAndCityNamePattern(value);
	}

	public static String[] extractZipOrCity(String cityOrZip) {
		String[] cityZip = new String[2];
		
		if (StringUtils.isEmpty(cityOrZip)) {
			throw new IllegalArgumentException(" The cityZip value is null");
		}
		
		cityZip = cityOrZip.split(SPLIT_EXPR);
		return cityZip;

	}	
}
