package com.ecom.domain;

import java.io.Serializable;

public enum AppartmentType implements Serializable {

	Etagewohnung, Erdgeschoss, Dachgeschoss, Maisonette, Penthouse, Loft, Sonstige;
	
	
    public static AppartmentType valueOf(int id) {

        switch (id) {
        case 0:
            return AppartmentType.Etagewohnung;
        case 1:
            return AppartmentType.Erdgeschoss;
        case 2:
            return AppartmentType.Dachgeschoss;
        case 3:
            return AppartmentType.Maisonette;
        case 4:
            return AppartmentType.Penthouse;
        case 5:
            return AppartmentType.Loft;
        default:
            return AppartmentType.Sonstige;
        }
    }	
    
    public static int valueOf(AppartmentType appartmentType) {

        switch (appartmentType) {
        case Etagewohnung:
            return 0;
        case Erdgeschoss:
            return 1;
        case Dachgeschoss:
            return 2;
        case Maisonette:
            return 3;
        case Penthouse:
            return 4;
        case Loft:
            return 5;
        default:
            return 6;
        }
    }    
}
