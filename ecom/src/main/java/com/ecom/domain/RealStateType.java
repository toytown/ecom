package com.ecom.domain;

public enum RealStateType {

    None("None"), House("House"), Appartment("Appartment"), FurnishedAppartment("Furnished Appartment"), Land("Land"), Garage("Garage"), VacationAppartment("VacationAppartment");
    
    private String value;
    
    private RealStateType(String val) {
        this.value = val;
    }
    
    @Override
    public String toString() {
        return value;
    }    
}
