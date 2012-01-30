package com.ecom.domain;

import java.io.Serializable;

public class SearchRequest implements Serializable {

    private static final long serialVersionUID = -2062880133131607502L;

    private String city;

    private Double areaFrom;
    
    private Double areaTo;

    private Double priceFrom;
    
    private Double priceTo;
    
    private Double roomsFrom;
    
    private Double roomsTo;

    private Integer searchType;
    
    private boolean isProvisionFree ;
    
    private boolean isKitchenAvailable;
    
    private boolean isFurnished;
    
    private boolean isBalconyAvailable;
    
    private boolean isLiftAvailable;
    
    private boolean isGardenAvailable;
    
    private Integer heatingTypeId;
    
    private RealStateSort sortOption;
    
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
		result = prime * result + ((areaFrom == null) ? 0 : areaFrom.hashCode());
		result = prime * result + ((areaTo == null) ? 0 : areaTo.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((heatingTypeId == null) ? 0 : heatingTypeId.hashCode());
		result = prime * result + (isBalconyAvailable ? 1231 : 1237);
		result = prime * result + (isFurnished ? 1231 : 1237);
		result = prime * result + (isGardenAvailable ? 1231 : 1237);
		result = prime * result + (isKitchenAvailable ? 1231 : 1237);
		result = prime * result + (isLiftAvailable ? 1231 : 1237);
		result = prime * result + (isProvisionFree ? 1231 : 1237);
		result = prime * result + ((priceFrom == null) ? 0 : priceFrom.hashCode());
		result = prime * result + ((priceTo == null) ? 0 : priceTo.hashCode());
		result = prime * result + ((roomsFrom == null) ? 0 : roomsFrom.hashCode());
		result = prime * result + ((roomsTo == null) ? 0 : roomsTo.hashCode());
		result = prime * result + ((searchType == null) ? 0 : searchType.hashCode());
		result = prime * result + ((sortOption == null) ? 0 : sortOption.hashCode());
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
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (heatingTypeId == null) {
			if (other.heatingTypeId != null)
				return false;
		} else if (!heatingTypeId.equals(other.heatingTypeId))
			return false;
		if (isBalconyAvailable != other.isBalconyAvailable)
			return false;
		if (isFurnished != other.isFurnished)
			return false;
		if (isGardenAvailable != other.isGardenAvailable)
			return false;
		if (isKitchenAvailable != other.isKitchenAvailable)
			return false;
		if (isLiftAvailable != other.isLiftAvailable)
			return false;
		if (isProvisionFree != other.isProvisionFree)
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
		if (sortOption != other.sortOption)
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

	public RealStateSort getSortOption() {
		return sortOption;
	}

	public void setSortOption(RealStateSort sortOption) {
		this.sortOption = sortOption;
	}

    
}
