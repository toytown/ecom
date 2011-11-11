package com.ecom.domain;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "realstate")
public class RealState implements Serializable {

	private static final long serialVersionUID = 4910191493640273023L;

	@Id
	private ObjectId id;

	private String title;

	private int status;

	private int originalPrice;

	private int typeId;

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

	private int condition;

	private int categoryId;

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

	private String lastRenovatedYear;

	private String otherInformation;

	private boolean provisionFree;

	private String provisionCondition;

	private String imageLocation;

	private boolean barrierFree;

	private boolean seniorAppartment;

	private String userName;

	private Date activationDate;
	   
	private Date insertedTs;

	private Date updatedTs;

	private String titleImage;

	@Transient
	private String addressInfo;
	
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public String getLastRenovatedYear() {
		return lastRenovatedYear;
	}

	public void setLastRenovatedYear(String lastRenovatedYear) {
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

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getTitleImageLocation(String imageRepository) {
		try {
			URL url =  new URL("http://localhost/image-repository" + "/" + getId() + "/" + getTitleImage());
			return url.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }
}
