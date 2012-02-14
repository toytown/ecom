package com.ecom.domain;

public enum RealStateType {

    None, House, Appartment, FurnishedAppartment, Land, Garage, VacationAppartment;

    public static RealStateType valueOf(int id) {

        switch (id) {
        case 0:
            return RealStateType.None;
        case 1:
            return RealStateType.House;
        case 2:
            return RealStateType.Appartment;
        case 3:
            return RealStateType.FurnishedAppartment;
        case 4:
            return RealStateType.Land;
        case 5:
            return RealStateType.Garage;
        case 6:
            return RealStateType.VacationAppartment;
        default:
            return RealStateType.None;
        }
    }

    public static int getId(RealStateType realStateType) {

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
