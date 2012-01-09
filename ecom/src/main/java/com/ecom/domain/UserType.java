package com.ecom.domain;

public enum UserType {

	PRIVATE, BUSINESS, OTHERS;
	
	public static UserType getUserTypeById(int val) {
		if (val == 0) {
			return PRIVATE;
		}
		
		if (val ==1) {
			return BUSINESS;
		}
		
		return OTHERS;
	}
}
