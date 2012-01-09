package com.ecom.domain;

public enum UserStatus {
	INACTIVE, ACTIVE, SUSPENDED, OTHERS;

	public static UserStatus getUserStatusById(int status) {

		if (status == 0) {
			return INACTIVE;
		} else if (status == 1) {
			return ACTIVE;
		} else if (status == 2) {
			return SUSPENDED;
		}

		return OTHERS;
	}
	
	public static int getStatusId(UserStatus status) {

		if (status.equals(INACTIVE)) {
			return 0;
		} else if (status.equals(ACTIVE)) {
			return 1;
		} else if (status.equals(SUSPENDED)) {
			return 2;
		}

		return -100;
	}
}
