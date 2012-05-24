package com.ecom.domain;

public enum RealStateCategory {

    None, House, Appartment, FurnishedAppartment, Land, Garage, VacationAppartment;

    public static RealStateCategory valueOf(int id) {

        switch (id) {
        case 0:
            return RealStateCategory.None;
        case 1:
            return RealStateCategory.House;
        case 2:
            return RealStateCategory.Appartment;
        case 3:
            return RealStateCategory.FurnishedAppartment;
        case 4:
            return RealStateCategory.Land;
        case 5:
            return RealStateCategory.Garage;
        case 6:
            return RealStateCategory.VacationAppartment;
        default:
            return RealStateCategory.None;
        }
    }

    public static int getId(RealStateCategory realStateType) {

        switch (realStateType) {
        case None:
            return 0;
        case House:
            return 1;
        case Appartment:
            return 2;
        case FurnishedAppartment:
            return 3;
        case Land:
            return 4;
        case Garage:
            return 5;
        case VacationAppartment:
            return 6;
        default:
            return 0;
        }
    }

}
