package com.ecom.domain;

import java.io.Serializable;

public class SearchRequest implements Serializable {

    private static final long serialVersionUID = -2062880133131607502L;

    private String cityOrZip;

    private Double areaFrom;

    private Double areaTo;

    private Double priceFrom;

    private Double priceTo;

    private Double roomsFrom;

    private Double roomsTo;

    private Integer searchType;

    private Boolean isProvisionFree;

    private Boolean isKitchenAvailable;

    private Boolean isFurnished;

    private Boolean isBalconyAvailable;

    private Boolean isLiftAvailable;

    private Boolean isGardenAvailable;

    private Integer heatingTypeId;

    private OfferType typeId = OfferType.Rent;

    private RealStateType realStateType = RealStateType.Appartment;

    private RealStateSort sortOrder;

    public String getCityOrZip() {
        return cityOrZip;
    }

    public void setCityOrZip(String cityOrZip) {
        this.cityOrZip = cityOrZip;
    }

    public Double getRoomsFrom() {
        return roomsFrom;
    }

    public void setRoomsFrom(Double roomsFrom) {
        this.roomsFrom = roomsFrom;
    }

    public Double getRoomsTo() {
        return roomsTo;
    }

    public void setRoomsTo(Double roomsTo) {
        this.roomsTo = roomsTo;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Double getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(Double areaFrom) {
        this.areaFrom = areaFrom;
    }

    public Double getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(Double areaTo) {
        this.areaTo = areaTo;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Boolean isProvisionFree() {
        return isProvisionFree;
    }

    public void setProvisionFree(Boolean isProvisionFree) {
        this.isProvisionFree = isProvisionFree;
    }

    public Boolean isKitchenAvailable() {
        return isKitchenAvailable;
    }

    public void setKitchenAvailable(Boolean isKitchenAvailable) {
        this.isKitchenAvailable = isKitchenAvailable;
    }

    public Boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(Boolean isFurnished) {
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
        result = prime * result + ((areaFrom == null) ? 0 : areaFrom.hashCode());
        result = prime * result + ((areaTo == null) ? 0 : areaTo.hashCode());
        result = prime * result + ((cityOrZip == null) ? 0 : cityOrZip.hashCode());
        result = prime * result + ((heatingTypeId == null) ? 0 : heatingTypeId.hashCode());
        result = prime * result + ((isBalconyAvailable == null) ? 0 : isBalconyAvailable.hashCode());
        result = prime * result + ((isFurnished == null) ? 0 : isFurnished.hashCode());
        result = prime * result + ((isGardenAvailable == null) ? 0 : isGardenAvailable.hashCode());
        result = prime * result + ((isKitchenAvailable == null) ? 0 : isKitchenAvailable.hashCode());
        result = prime * result + ((isLiftAvailable == null) ? 0 : isLiftAvailable.hashCode());
        result = prime * result + ((isProvisionFree == null) ? 0 : isProvisionFree.hashCode());
        result = prime * result + ((priceFrom == null) ? 0 : priceFrom.hashCode());
        result = prime * result + ((priceTo == null) ? 0 : priceTo.hashCode());
        result = prime * result + ((roomsFrom == null) ? 0 : roomsFrom.hashCode());
        result = prime * result + ((roomsTo == null) ? 0 : roomsTo.hashCode());
        result = prime * result + ((searchType == null) ? 0 : searchType.hashCode());
        result = prime * result + ((sortOrder == null) ? 0 : sortOrder.hashCode());
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
        if (areaFrom == null) {
            if (other.areaFrom != null)
                return false;
        } else if (!areaFrom.equals(other.areaFrom))
            return false;
        if (areaTo == null) {
            if (other.areaTo != null)
                return false;
        } else if (!areaTo.equals(other.areaTo))
            return false;
        if (cityOrZip == null) {
            if (other.cityOrZip != null)
                return false;
        } else if (!cityOrZip.equals(other.cityOrZip))
            return false;
        if (heatingTypeId == null) {
            if (other.heatingTypeId != null)
                return false;
        } else if (!heatingTypeId.equals(other.heatingTypeId))
            return false;
        if (isBalconyAvailable == null) {
            if (other.isBalconyAvailable != null)
                return false;
        } else if (!isBalconyAvailable.equals(other.isBalconyAvailable))
            return false;
        if (isFurnished == null) {
            if (other.isFurnished != null)
                return false;
        } else if (!isFurnished.equals(other.isFurnished))
            return false;
        if (isGardenAvailable == null) {
            if (other.isGardenAvailable != null)
                return false;
        } else if (!isGardenAvailable.equals(other.isGardenAvailable))
            return false;
        if (isKitchenAvailable == null) {
            if (other.isKitchenAvailable != null)
                return false;
        } else if (!isKitchenAvailable.equals(other.isKitchenAvailable))
            return false;
        if (isLiftAvailable == null) {
            if (other.isLiftAvailable != null)
                return false;
        } else if (!isLiftAvailable.equals(other.isLiftAvailable))
            return false;
        if (isProvisionFree == null) {
            if (other.isProvisionFree != null)
                return false;
        } else if (!isProvisionFree.equals(other.isProvisionFree))
            return false;
        if (priceFrom == null) {
            if (other.priceFrom != null)
                return false;
        } else if (!priceFrom.equals(other.priceFrom))
            return false;
        if (priceTo == null) {
            if (other.priceTo != null)
                return false;
        } else if (!priceTo.equals(other.priceTo))
            return false;
        if (roomsFrom == null) {
            if (other.roomsFrom != null)
                return false;
        } else if (!roomsFrom.equals(other.roomsFrom))
            return false;
        if (roomsTo == null) {
            if (other.roomsTo != null)
                return false;
        } else if (!roomsTo.equals(other.roomsTo))
            return false;
        if (searchType == null) {
            if (other.searchType != null)
                return false;
        } else if (!searchType.equals(other.searchType))
            return false;
        if (sortOrder != other.sortOrder)
            return false;
        return true;
    }

    public Boolean isBalconyAvailable() {
        return isBalconyAvailable;
    }

    public void setBalconyAvailable(Boolean isBalconyAvailable) {
        this.isBalconyAvailable = isBalconyAvailable;
    }

    public Boolean isLiftAvailable() {
        return isLiftAvailable;
    }

    public void setLiftAvailable(Boolean isLiftAvailable) {
        this.isLiftAvailable = isLiftAvailable;
    }

    public Boolean isGardenAvailable() {
        return isGardenAvailable;
    }

    public void setGardenAvailable(Boolean isGardenAvailable) {
        this.isGardenAvailable = isGardenAvailable;
    }

    public RealStateSort getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(RealStateSort sortOption) {
        this.sortOrder = sortOption;
    }

    public OfferType getTypeId() {
        return typeId;
    }

    public void setTypeId(OfferType typeId) {
        this.typeId = typeId;
    }

    public RealStateType getRealStateType() {
        return realStateType;
    }

    public void setRealStateType(RealStateType realStateType) {
        this.realStateType = realStateType;
    }

}
