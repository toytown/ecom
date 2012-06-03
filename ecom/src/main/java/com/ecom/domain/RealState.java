package com.ecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecom.common.utils.GeoLocationUtils;

@Document(collection = "realstate")
@CompoundIndexes({
    @CompoundIndex(name = "loc_cost_size_room_idx", def = "{'location': 1, 'cost': 1, 'size': -1, 'totalRooms': -1}")
})
public class RealState implements Serializable {

	private static final long serialVersionUID = 4910191493640273023L;

	@Id
	private ObjectId id;

	private String title;

	private int status;

	private int originalPrice;

	// offer type - Rent, Buy
	private OfferType offerType;

	// real state category- House, Appartment, garage
	private RealStateCategory realStateCategory;

    private AppartmentType appartmentType;
    
    private HouseType houseType;
    
	private String areaCode;

	private String city;

	private String street;

	private String houseNo;

	private double size;

	private double cost;

	private double floor;

	private double totalFloors;

	private double totalRooms;

	private int bedRooms;

	private int bathRooms;

	private boolean toiletWithBathRoom;

	private boolean cellarAvailable;

	private boolean balconyAvailable;

	private boolean liftAvailable;

	private boolean gardenAvailable;

	private Condition condition;

	private int heatingTypeId;

	private double additionalCost;

	private boolean heatingCostIncluded;

	private double depositPeriod;

	private Date availableFrom;

	private boolean garageAvailable;

	private double garageCost;

	private String description;

	private String areaDescription;

	private String fittings;

	private boolean energyPassAvailable;

	private boolean kitchenAvailable;

	private boolean furnished;

	private boolean animalsAllowed;

	private int builtYear;

	private int lastRenovatedYear;

	private String otherInformation;

	private boolean provisionFree;

	private String provisionCondition;

    private boolean isRented;
    
	private String imageLocation;

	private boolean barrierFree;

	private boolean seniorAppartment;

	private boolean isHistoricalObject;
	
	private String userId;

	private Date activationDate;

	//erbpacht
	private boolean leasing;
	
	//GRZ
	private String coverageRatio;
	
	//GFZ
	private String floorSpaceRatio;
	
	private boolean buildingPermission;
	
	private boolean tearedDown;
	
	private boolean shortlyDevelopable;
	
	private boolean distributable;
	
	private boolean minDistributionArea;
	
	private boolean topListed;
	
	private boolean promiPlacement;
	
	private GarageType garageType;
	
	private OffererType offererType;
	
	private BuiltStatus builtStatus;

	private int noOfParkPlaces;
	
	private boolean hasMultiPhaseElectricalSupply;
	
	private boolean hasCafeteria;
	
	private FlooringType flooringType;
	
	private boolean hasAirConidtion;
	
	private String nearestStation;
	
	private int timeToNearestPublicTransport;
	
	private int timeToAirportTravel;
	
	@GeoSpatialIndexed
	private Double[] location;

    private Date insertedTs;

    private Date updatedTs;
    
	private List<RealStateImage> images = new ArrayList<RealStateImage>();

	public enum STATUS {
		NEW, VALID, ACTIVE, INACTIVE;

		public static int getValue(STATUS status) {

			switch (status) {
			case VALID:
				return 1;
			case ACTIVE:
				return 2;
			case INACTIVE:
				return 100;
			case NEW:
				return 0;
			default:
				return 0;
			}
		}
	};

	@Transient
	private String addressInfo;

	private ContactInfo contactInfo;

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public RealState() {
		super();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getFloor() {
		return floor;
	}

	public void setFloor(double floor) {
		this.floor = floor;
	}

	public double getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(double totalFloors) {
		this.totalFloors = totalFloors;
	}

	public double getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(double totalRooms) {
		this.totalRooms = totalRooms;
	}

	public int getBedRooms() {
		return bedRooms;
	}

	public void setBedRooms(int bedRooms) {
		this.bedRooms = bedRooms;
	}

	public int getBathRooms() {
		return bathRooms;
	}

	public void setBathRooms(int bathRooms) {
		this.bathRooms = bathRooms;
	}

	public boolean isToiletWithBathRoom() {
		return toiletWithBathRoom;
	}

	public void setToiletWithBathRoom(boolean toiletWithBathRoom) {
		this.toiletWithBathRoom = toiletWithBathRoom;
	}

	public boolean isCellarAvailable() {
		return cellarAvailable;
	}

	public void setCellarAvailable(boolean cellarAvailable) {
		this.cellarAvailable = cellarAvailable;
	}

	public boolean isBalconyAvailable() {
		return balconyAvailable;
	}

	public void setBalconyAvailable(boolean balconyAvailable) {
		this.balconyAvailable = balconyAvailable;
	}

	public boolean isLiftAvailable() {
		return liftAvailable;
	}

	public void setLiftAvailable(boolean liftAvailable) {
		this.liftAvailable = liftAvailable;
	}

	public boolean isGardenAvailable() {
		return gardenAvailable;
	}

	public void setGardenAvailable(boolean gardenAvailable) {
		this.gardenAvailable = gardenAvailable;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public int getHeatingTypeId() {
		return heatingTypeId;
	}

	public void setHeatingTypeId(int heatingTypeId) {
		this.heatingTypeId = heatingTypeId;
	}

	public double getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(double additionalCost) {
		this.additionalCost = additionalCost;
	}

	public boolean isHeatingCostIncluded() {
		return heatingCostIncluded;
	}

	public void setHeatingCostIncluded(boolean heatingCostIncluded) {
		this.heatingCostIncluded = heatingCostIncluded;
	}

	public double getDepositPeriod() {
		return depositPeriod;
	}

	public void setDepositPeriod(double depositPeriod) {
		this.depositPeriod = depositPeriod;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public boolean isGarageAvailable() {
		return garageAvailable;
	}

	public void setGarageAvailable(boolean garageAvailable) {
		this.garageAvailable = garageAvailable;
	}

	public double getGarageCost() {
		return garageCost;
	}

	public void setGarageCost(double garageCost) {
		this.garageCost = garageCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAreaDescription() {
		return areaDescription;
	}

	public void setAreaDescription(String areaDescription) {
		this.areaDescription = areaDescription;
	}

	public String getFittings() {
		return fittings;
	}

	public void setFittings(String fittings) {
		this.fittings = fittings;
	}

	public boolean isEnergyPassAvailable() {
		return energyPassAvailable;
	}

	public void setEnergyPassAvailable(boolean energyPassAvailable) {
		this.energyPassAvailable = energyPassAvailable;
	}

	public boolean isKitchenAvailable() {
		return kitchenAvailable;
	}

	public void setKitchenAvailable(boolean kitchenAvailable) {
		this.kitchenAvailable = kitchenAvailable;
	}

	public boolean isFurnished() {
		return furnished;
	}

	public void setFurnished(boolean furnished) {
		this.furnished = furnished;
	}

	public boolean isAnimalsAllowed() {
		return animalsAllowed;
	}

	public void setAnimalsAllowed(boolean animalsAllowed) {
		this.animalsAllowed = animalsAllowed;
	}

	public int getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(int builtYear) {
		this.builtYear = builtYear;
	}

	public int getLastRenovatedYear() {
		return lastRenovatedYear;
	}

	public void setLastRenovatedYear(int lastRenovatedYear) {
		this.lastRenovatedYear = lastRenovatedYear;
	}

	public String getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}

	public boolean isProvisionFree() {
		return provisionFree;
	}

	public void setProvisionFree(boolean provisionFree) {
		this.provisionFree = provisionFree;
	}

	public String getProvisionCondition() {
		return provisionCondition;
	}

	public void setProvisionCondition(String provisionCondition) {
		this.provisionCondition = provisionCondition;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public boolean isBarrierFree() {
		return barrierFree;
	}

	public void setBarrierFree(boolean barrierFree) {
		this.barrierFree = barrierFree;
	}

	public boolean isSeniorAppartment() {
		return seniorAppartment;
	}

	public void setSeniorAppartment(boolean seniorAppartment) {
		this.seniorAppartment = seniorAppartment;
	}

	public Date getInsertedTs() {
		return insertedTs;
	}

	public void setInsertedTs(Date insertedTs) {
		this.insertedTs = insertedTs;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public String getTitleThumbNailImage() {

		String value = "";
		for (RealStateImage img : this.getImages()) {

			if (img != null && img.getId() != null && img.isTitleImage() && img.isThumbNail()) {
				return img.getId().toString();
			}
		}

		return value;
	}

	public String getTitleImageId() {

		String value = "";
		for (RealStateImage img : this.getImages()) {

			if (img != null && img.getId() != null && img.isTitleImage() && !img.isThumbNail()) {
				return img.getId().toString();
			}
		}

		return value;
	}

	/**
	 * Returns both actual and thumbnail image
	 * 
	 * @return
	 */
	public List<RealStateImage> getTitleImages() {
		List<RealStateImage> realStateImgList = new ArrayList<RealStateImage>();

		for (RealStateImage img : this.getImages()) {
			if (img != null && img.getId() != null && img.isTitleImage()) {
				realStateImgList.add(img);
			}
		}

		return realStateImgList;
	}

	public void removeTitleImages() {

		Iterator<RealStateImage> iter = this.getImages().iterator();

		while (iter.hasNext()) {
			RealStateImage img = iter.next();

			if (img != null && img.isTitleImage()) {
				iter.remove();
			}
		}
	}

	public void addTitleImages(List<RealStateImage> titlImages) {
		if (titlImages.isEmpty()) {
			return;
		}

		Iterator<RealStateImage> iter = getImages().iterator();

		// removes previously stored title images
		while (iter.hasNext()) {
			RealStateImage img = iter.next();
			if (img.isTitleImage()) {
				iter.remove();
			}
		}
		this.getImages().addAll(titlImages);
	}


	public String getAddressInfo() {
		StringBuilder addressInfo = new StringBuilder("");
		addressInfo.append(StringUtils.trimToEmpty(getStreet()));
		addressInfo.append(", ");
		addressInfo.append(StringUtils.trimToEmpty(getAreaCode()));
		addressInfo.append(", ");
		addressInfo.append(StringUtils.trimToEmpty(getCity()));
		this.addressInfo = addressInfo.toString();
		return this.addressInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public List<RealStateImage> getImages() {
		return images;
	}

	public void setImages(List<RealStateImage> images) {
		this.images = images;
	}

	public List<RealStateImage> getGalleryImages() {
		List<RealStateImage> galleryImages = new ArrayList<RealStateImage>();
		for (RealStateImage galleryImage : this.images) {
			if (!galleryImage.isThumbNail()) {
				galleryImages.add(galleryImage);
			}
		}

		return galleryImages;
	}

	public List<RealStateImage> getNonTitleImages() {
		List<RealStateImage> nonTitleImages = new ArrayList<RealStateImage>();
		for (RealStateImage img : this.images) {
			if (!img.isTitleImage() && !img.isThumbNail()) {
				nonTitleImages.add(img);
			}
		}

		return nonTitleImages;
	}


	public boolean isValid() {

		if (StringUtils.isEmpty(getTitle())) {
			return false;
		}

		if (StringUtils.isEmpty(getDescription())) {
			return false;
		}

		if (StringUtils.isEmpty(this.getStreet())) {
			return false;
		}

		if (StringUtils.isEmpty(this.getCity())) {
			return false;
		}

		if (StringUtils.isEmpty(this.getAreaCode())) {
			return false;
		}

		return true;
	}

	public void changeStatus() {

		if (isValid()) {
			this.setStatus(STATUS.getValue(STATUS.VALID));
		}
	}

	public void activate(Date activationDate) {

		if (STATUS.getValue(STATUS.VALID) == this.getStatus()) {
			this.setActivationDate(activationDate);
			this.setStatus(STATUS.getValue(STATUS.ACTIVE));
		}
	}

	public String getAddress() {

		StringBuilder address = new StringBuilder();

		if (StringUtils.isNotEmpty(this.getStreet()) && this.getStreet().trim().length() >= 2) {

			address.append(this.getStreet());

			if (StringUtils.isNotEmpty(this.getHouseNo())) {
				address.append(" ");
				address.append(this.getHouseNo());
			}
		}

		if (StringUtils.isNotEmpty(this.getAreaCode()) && this.getAreaCode().trim().length() >= 2) {
			address.append(", ");
			address.append(this.getAreaCode());
		}

		if (StringUtils.isNotEmpty(this.getCity()) && this.getCity().trim().length() >= 2) {
			address.append(", ");
			address.append(this.getCity());
		}

		return address.toString();
	}

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}

	public boolean hasValidMinimumAddressForGeoCoding() {
		if (StringUtils.isEmpty(getAddress())) {
			return false;
		}

		if (GeoLocationUtils.hasOnlyCityNamePattern(this.getCity())) {
			return true;
		}

		if (GeoLocationUtils.isZipCodeOnly(this.getAreaCode())) {
			return true;
		}

		return false;
	}

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }


    public AppartmentType getAppartmentType() {
        return appartmentType;
    }

    public void setAppartmentType(AppartmentType appartmentType) {
        this.appartmentType = appartmentType;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public RealStateCategory getRealStateCategory() {
        return realStateCategory;
    }

    public void setRealStateCategory(RealStateCategory realStateCategory) {
        this.realStateCategory = realStateCategory;
    }

	public boolean isHistoricalObject() {
		return isHistoricalObject;
	}

	public void setHistoricalObject(boolean isHistoricalObject) {
		this.isHistoricalObject = isHistoricalObject;
	}

    public boolean isLeasing() {
        return leasing;
    }

    public void setLeasing(boolean leasing) {
        this.leasing = leasing;
    }

    public String getCoverageRatio() {
        return coverageRatio;
    }

    public void setCoverageRatio(String coverageRatio) {
        this.coverageRatio = coverageRatio;
    }

    public String getFloorSpaceRatio() {
        return floorSpaceRatio;
    }

    public void setFloorSpaceRatio(String floorSpaceRatio) {
        this.floorSpaceRatio = floorSpaceRatio;
    }

    public boolean isBuildingPermission() {
        return buildingPermission;
    }

    public void setBuildingPermission(boolean buildingPermission) {
        this.buildingPermission = buildingPermission;
    }

    public boolean isTearedDown() {
        return tearedDown;
    }

    public void setTearedDown(boolean tearedDown) {
        this.tearedDown = tearedDown;
    }

    public boolean isShortlyDevelopable() {
        return shortlyDevelopable;
    }

    public void setShortlyDevelopable(boolean shortlyDevelopable) {
        this.shortlyDevelopable = shortlyDevelopable;
    }

    public boolean isDistributable() {
        return distributable;
    }

    public void setDistributable(boolean distributable) {
        this.distributable = distributable;
    }

    public boolean isMinDistributionArea() {
        return minDistributionArea;
    }

    public void setMinDistributionArea(boolean minDistributionArea) {
        this.minDistributionArea = minDistributionArea;
    }

    public boolean isTopListed() {
        return topListed;
    }

    public void setTopListed(boolean topListed) {
        this.topListed = topListed;
    }

    public boolean isPromiPlacement() {
        return promiPlacement;
    }

    public void setPromiPlacement(boolean promiPlacement) {
        this.promiPlacement = promiPlacement;
    }

    public GarageType getGarageType() {
        return garageType;
    }

    public void setGarageType(GarageType garageType) {
        this.garageType = garageType;
    }

    public OffererType getOffererType() {
        return offererType;
    }

    public void setOffererType(OffererType offererType) {
        this.offererType = offererType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activationDate == null) ? 0 : activationDate.hashCode());
        long temp;
        temp = Double.doubleToLongBits(additionalCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((addressInfo == null) ? 0 : addressInfo.hashCode());
        result = prime * result + (animalsAllowed ? 1231 : 1237);
        result = prime * result + ((appartmentType == null) ? 0 : appartmentType.hashCode());
        result = prime * result + ((areaCode == null) ? 0 : areaCode.hashCode());
        result = prime * result + ((areaDescription == null) ? 0 : areaDescription.hashCode());
        result = prime * result + ((availableFrom == null) ? 0 : availableFrom.hashCode());
        result = prime * result + (balconyAvailable ? 1231 : 1237);
        result = prime * result + (barrierFree ? 1231 : 1237);
        result = prime * result + bathRooms;
        result = prime * result + bedRooms;
        result = prime * result + (buildingPermission ? 1231 : 1237);
        result = prime * result + builtYear;
        result = prime * result + (cellarAvailable ? 1231 : 1237);
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((condition == null) ? 0 : condition.hashCode());
        result = prime * result + ((contactInfo == null) ? 0 : contactInfo.hashCode());
        temp = Double.doubleToLongBits(cost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((coverageRatio == null) ? 0 : coverageRatio.hashCode());
        temp = Double.doubleToLongBits(depositPeriod);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (distributable ? 1231 : 1237);
        result = prime * result + (energyPassAvailable ? 1231 : 1237);
        result = prime * result + ((fittings == null) ? 0 : fittings.hashCode());
        temp = Double.doubleToLongBits(floor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((floorSpaceRatio == null) ? 0 : floorSpaceRatio.hashCode());
        result = prime * result + (furnished ? 1231 : 1237);
        result = prime * result + (garageAvailable ? 1231 : 1237);
        temp = Double.doubleToLongBits(garageCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((garageType == null) ? 0 : garageType.hashCode());
        result = prime * result + (gardenAvailable ? 1231 : 1237);
        result = prime * result + (heatingCostIncluded ? 1231 : 1237);
        result = prime * result + heatingTypeId;
        result = prime * result + ((houseNo == null) ? 0 : houseNo.hashCode());
        result = prime * result + ((houseType == null) ? 0 : houseType.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((insertedTs == null) ? 0 : insertedTs.hashCode());
        result = prime * result + (isHistoricalObject ? 1231 : 1237);
        result = prime * result + (isRented ? 1231 : 1237);
        result = prime * result + (kitchenAvailable ? 1231 : 1237);
        result = prime * result + lastRenovatedYear;
        result = prime * result + (leasing ? 1231 : 1237);
        result = prime * result + (liftAvailable ? 1231 : 1237);
        result = prime * result + Arrays.hashCode(location);
        result = prime * result + (minDistributionArea ? 1231 : 1237);
        result = prime * result + ((offerType == null) ? 0 : offerType.hashCode());
        result = prime * result + ((offererType == null) ? 0 : offererType.hashCode());
        result = prime * result + originalPrice;
        result = prime * result + ((otherInformation == null) ? 0 : otherInformation.hashCode());
        result = prime * result + (promiPlacement ? 1231 : 1237);
        result = prime * result + ((provisionCondition == null) ? 0 : provisionCondition.hashCode());
        result = prime * result + (provisionFree ? 1231 : 1237);
        result = prime * result + ((realStateCategory == null) ? 0 : realStateCategory.hashCode());
        result = prime * result + (seniorAppartment ? 1231 : 1237);
        result = prime * result + (shortlyDevelopable ? 1231 : 1237);
        temp = Double.doubleToLongBits(size);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + status;
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + (tearedDown ? 1231 : 1237);
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + (toiletWithBathRoom ? 1231 : 1237);
        result = prime * result + (topListed ? 1231 : 1237);
        temp = Double.doubleToLongBits(totalFloors);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalRooms);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        RealState other = (RealState) obj;
        if (activationDate == null) {
            if (other.activationDate != null)
                return false;
        } else if (!activationDate.equals(other.activationDate))
            return false;
        if (Double.doubleToLongBits(additionalCost) != Double.doubleToLongBits(other.additionalCost))
            return false;
        if (addressInfo == null) {
            if (other.addressInfo != null)
                return false;
        } else if (!addressInfo.equals(other.addressInfo))
            return false;
        if (animalsAllowed != other.animalsAllowed)
            return false;
        if (appartmentType != other.appartmentType)
            return false;
        if (areaCode == null) {
            if (other.areaCode != null)
                return false;
        } else if (!areaCode.equals(other.areaCode))
            return false;
        if (areaDescription == null) {
            if (other.areaDescription != null)
                return false;
        } else if (!areaDescription.equals(other.areaDescription))
            return false;
        if (availableFrom == null) {
            if (other.availableFrom != null)
                return false;
        } else if (!availableFrom.equals(other.availableFrom))
            return false;
        if (balconyAvailable != other.balconyAvailable)
            return false;
        if (barrierFree != other.barrierFree)
            return false;
        if (bathRooms != other.bathRooms)
            return false;
        if (bedRooms != other.bedRooms)
            return false;
        if (buildingPermission != other.buildingPermission)
            return false;
        if (builtYear != other.builtYear)
            return false;
        if (cellarAvailable != other.cellarAvailable)
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (condition != other.condition)
            return false;
        if (contactInfo == null) {
            if (other.contactInfo != null)
                return false;
        } else if (!contactInfo.equals(other.contactInfo))
            return false;
        if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
            return false;
        if (coverageRatio == null) {
            if (other.coverageRatio != null)
                return false;
        } else if (!coverageRatio.equals(other.coverageRatio))
            return false;
        if (Double.doubleToLongBits(depositPeriod) != Double.doubleToLongBits(other.depositPeriod))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (distributable != other.distributable)
            return false;
        if (energyPassAvailable != other.energyPassAvailable)
            return false;
        if (fittings == null) {
            if (other.fittings != null)
                return false;
        } else if (!fittings.equals(other.fittings))
            return false;
        if (Double.doubleToLongBits(floor) != Double.doubleToLongBits(other.floor))
            return false;
        if (floorSpaceRatio == null) {
            if (other.floorSpaceRatio != null)
                return false;
        } else if (!floorSpaceRatio.equals(other.floorSpaceRatio))
            return false;
        if (furnished != other.furnished)
            return false;
        if (garageAvailable != other.garageAvailable)
            return false;
        if (Double.doubleToLongBits(garageCost) != Double.doubleToLongBits(other.garageCost))
            return false;
        if (garageType != other.garageType)
            return false;
        if (gardenAvailable != other.gardenAvailable)
            return false;
        if (heatingCostIncluded != other.heatingCostIncluded)
            return false;
        if (heatingTypeId != other.heatingTypeId)
            return false;
        if (houseNo == null) {
            if (other.houseNo != null)
                return false;
        } else if (!houseNo.equals(other.houseNo))
            return false;
        if (houseType != other.houseType)
            return false;
        if (images == null) {
            if (other.images != null)
                return false;
        } else if (!images.equals(other.images))
            return false;
        if (insertedTs == null) {
            if (other.insertedTs != null)
                return false;
        } else if (!insertedTs.equals(other.insertedTs))
            return false;
        if (isHistoricalObject != other.isHistoricalObject)
            return false;
        if (isRented != other.isRented)
            return false;
        if (kitchenAvailable != other.kitchenAvailable)
            return false;
        if (lastRenovatedYear != other.lastRenovatedYear)
            return false;
        if (leasing != other.leasing)
            return false;
        if (liftAvailable != other.liftAvailable)
            return false;
        if (!Arrays.equals(location, other.location))
            return false;
        if (minDistributionArea != other.minDistributionArea)
            return false;
        if (offerType != other.offerType)
            return false;
        if (offererType != other.offererType)
            return false;
        if (originalPrice != other.originalPrice)
            return false;
        if (otherInformation == null) {
            if (other.otherInformation != null)
                return false;
        } else if (!otherInformation.equals(other.otherInformation))
            return false;
        if (promiPlacement != other.promiPlacement)
            return false;
        if (provisionCondition == null) {
            if (other.provisionCondition != null)
                return false;
        } else if (!provisionCondition.equals(other.provisionCondition))
            return false;
        if (provisionFree != other.provisionFree)
            return false;
        if (realStateCategory != other.realStateCategory)
            return false;
        if (seniorAppartment != other.seniorAppartment)
            return false;
        if (shortlyDevelopable != other.shortlyDevelopable)
            return false;
        if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
            return false;
        if (status != other.status)
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (tearedDown != other.tearedDown)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (toiletWithBathRoom != other.toiletWithBathRoom)
            return false;
        if (topListed != other.topListed)
            return false;
        if (Double.doubleToLongBits(totalFloors) != Double.doubleToLongBits(other.totalFloors))
            return false;
        if (Double.doubleToLongBits(totalRooms) != Double.doubleToLongBits(other.totalRooms))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    public BuiltStatus getBuiltStatus() {
        return builtStatus;
    }

    public void setBuiltStatus(BuiltStatus builtStatus) {
        this.builtStatus = builtStatus;
    }
}
