package com.ecom.domain;

public enum OfferType {

    None, Rent, Buy;

    public static OfferType valueOf(int id) {

        switch (id) {
        case 0:
            return OfferType.None;
        case 1:
            return OfferType.Rent;
        case 2:
            return OfferType.Buy;
        default:
            return OfferType.None;
        }
    }

    public static int getId(OfferType offerType) {

        switch (offerType) {
        case None:
            return 0;
        case Rent:
            return 1;
        case Buy:
            return 2;
        default:
            return 0;
        }
    }
}
