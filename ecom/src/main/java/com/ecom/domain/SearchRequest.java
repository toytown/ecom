package com.ecom.domain;

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
    
    private boolean isProvisionFree ;
    
    private boolean isKitchenAvailable;
    
    private boolean isFurnished;
    
    private boolean isBalconyAvailable;
    
    private boolean isLiftAvailable;
    
    private boolean isGardenAvailable;
    
    private int heatingTypeId;
    
    
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

    public boolean isProvisionFree() {
        return isProvisionFree;
    }

    public void setProvisionFree(boolean isProvisionFree) {
        this.isProvisionFree = isProvisionFree;
    }

    public boolean isKitchenAvailable() {
        return isKitchenAvailable;
    }

    public void setKitchenAvailable(boolean isKitchenAvailable) {
        this.isKitchenAvailable = isKitchenAvailable;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean isFurnished) {
        this.isFurnished = isFurnished;
    }

    public int getHeatingTypeId() {
        return heatingTypeId;
    }

    public void setHeatingTypeId(int heatingTypeId) {
        this.heatingTypeId = heatingTypeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(areaFrom);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(areaTo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + heatingTypeId;
        result = prime * result + (isFurnished ? 1231 : 1237);
        result = prime * result + (isKitchenAvailable ? 1231 : 1237);
        result = prime * result + (isProvisionFree ? 1231 : 1237);
        temp = Double.doubleToLongBits(priceFrom);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priceTo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + roomsFrom;
        result = prime * result + roomsTo;
        result = prime * result + searchType;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SearchRequest other = (SearchRequest) obj;
        if (Double.doubleToLongBits(areaFrom) != Double.doubleToLongBits(other.areaFrom))
            return false;
        if (Double.doubleToLongBits(areaTo) != Double.doubleToLongBits(other.areaTo))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (heatingTypeId != other.heatingTypeId)
            return false;
        if (isFurnished != other.isFurnished)
            return false;
        if (isKitchenAvailable != other.isKitchenAvailable)
            return false;
        if (isProvisionFree != other.isProvisionFree)
            return false;
        if (Double.doubleToLongBits(priceFrom) != Double.doubleToLongBits(other.priceFrom))
            return false;
        if (Double.doubleToLongBits(priceTo) != Double.doubleToLongBits(other.priceTo))
            return false;
        if (roomsFrom != other.roomsFrom)
            return false;
        if (roomsTo != other.roomsTo)
            return false;
        if (searchType != other.searchType)
            return false;
        return true;
    }

	public boolean isBalconyAvailable() {
		return isBalconyAvailable;
	}

	public void setBalconyAvailable(boolean isBalconyAvailable) {
		this.isBalconyAvailable = isBalconyAvailable;
	}

	public boolean isLiftAvailable() {
		return isLiftAvailable;
	}

	public void setLiftAvailable(boolean isLiftAvailable) {
		this.isLiftAvailable = isLiftAvailable;
	}

	public boolean isGardenAvailable() {
		return isGardenAvailable;
	}

	public void setGardenAvailable(boolean isGardenAvailable) {
		this.isGardenAvailable = isGardenAvailable;
	}
    
}
