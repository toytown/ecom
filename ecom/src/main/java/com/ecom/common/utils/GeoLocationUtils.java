package com.ecom.common.utils;

import org.apache.commons.lang.StringUtils;

public class GeoLocationUtils {

	private static final String SPLIT_EXPR = "[,\\s.]+";

	    
	public static boolean isZipCodeOnly(String value) {
		return StringUtils.isNotEmpty(value) && StringUtils.isNumeric(value);
	}

	public static boolean isCityNameOnly(String value) {
		return StringUtils.isNotEmpty(value) && StringUtils.isAlpha(value);
	}

	public static boolean isBothZipAndCity(String value) {
		return StringUtils.isNotEmpty(value) && StringUtils.isAlphanumeric(value);
	}

	public static String[] extractZipOrCity(String cityOrZip) {
		String[] cityZip = new String[2];
		
		if (StringUtils.isNotEmpty(cityOrZip)) {
			cityZip = cityOrZip.split(SPLIT_EXPR);
		} else {
			return new String[] {cityOrZip, ""};
		}
		
		return cityZip;

	}	
}
