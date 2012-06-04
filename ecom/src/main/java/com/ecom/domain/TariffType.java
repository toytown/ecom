package com.ecom.domain;

import java.io.Serializable;

public enum TariffType implements Serializable {
	Free, Profi, ProfiWithTopListing;

	private static final int DEFAULT_UPLOADS = 5;
	private static final int PROFI_UPLOADS = 15;
	
	public static int getAllowdNoOfUploads(TariffType t) {
		switch (t) {
		case Free:
			return DEFAULT_UPLOADS;
		case Profi:
			return PROFI_UPLOADS;	
		case ProfiWithTopListing:
			return PROFI_UPLOADS;			
		default:
			return DEFAULT_UPLOADS;
		}

	}
	
	public static int getNoOfDaysOnline(TariffType t) {
		switch (t) {
		case Free:
			return DEFAULT_UPLOADS;
		case Profi:
			return PROFI_UPLOADS;	
		case ProfiWithTopListing:
			return PROFI_UPLOADS;			
		default:
			return DEFAULT_UPLOADS;
		}

	}	
}
