package com.ecom.web.search;

import java.io.Serializable;

public class SearchRequest implements Serializable {

    private static final long serialVersionUID = -2062880133131607502L;

    private String city;
    
    private double area;
    
    private double price;
    
    private int roomsFrom;
    
    private int roomsTo;

    private int searchType;
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double areaCode) {
        this.area = areaCode;
    }

    public double getPriceMax() {
        return price;
    }

    public void setPriceMax(double priceMax) {
        this.price = priceMax;
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
    
}
