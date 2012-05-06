package com.ecom.domain;

public enum OfferType {

    None, Rent, Buy, Lease, Auction;

    public static OfferType valueOf(int id) {

        switch (id) {
        case 0:
            return OfferType.None;
        case 1:
            return OfferType.Rent;
        case 2:
            return OfferType.Buy;
        case 3:
            return OfferType.Lease;
        case 4:
            return OfferType.Auction;
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
        case Lease:
            return 3;
        case Auction:
            return 4;            
        default:
            return 0;
        }
    }
}
