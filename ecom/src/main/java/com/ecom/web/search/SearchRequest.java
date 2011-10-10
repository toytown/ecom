package com.ecom.web.search;

import java.io.Serializable;

public class SearchRequest implements Serializable {

    private static final long serialVersionUID = -2062880133131607502L;

    private String city;

    private double areaFrom;
    
    private double areaTo;

    private double priceFrom;
    
    private double priceTo;
    
    private int roomsFrom;
    
    private int roomsTo;

    private int searchType;
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRoomsFrom() {
        return roomsFrom;
    }

    public void setRoomsFrom(int roomsFrom) {
        this.roomsFrom = roomsFrom;
    }

    public int getRoomsTo() {
        return roomsTo;
    }

    public void setRoomsTo(int roomsTo) {
        this.roomsTo = roomsTo;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public double getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(double areaFrom) {
        this.areaFrom = areaFrom;
    }

    public double getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(double areaTo) {
        this.areaTo = areaTo;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }
    
}
