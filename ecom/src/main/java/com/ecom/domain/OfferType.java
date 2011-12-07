package com.ecom.domain;

public enum OfferType {

    None("None"), Rent("Rent"), Buy("Buy");
    
    private String value;
    
    private OfferType(String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return value;
    }
    

}
