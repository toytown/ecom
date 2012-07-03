package com.ecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
@CompoundIndexes({ @CompoundIndex(name = "loc_cost_size_room_idx", def = "{'location': 1, 'cost': 1, 'size': -1, 'totalRooms': -1}") })
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

	//neben-flfäche
	private double surroundingAreaSize;
	
	private double cost;

	private double floor;

	private double totalFloors;

	private double totalRooms;

	private int bedRooms;

	private int bathRooms;

	private boolean toiletWithBathRoom;

	private boolean cellarAvailable;

	private boolean balconyAvailable;

	private boolean elevatorAvailable;

	private boolean commercialElevator;
	
	private double personalElevatorCapacity;
	
	private boolean craneAvailable;
	
	private double craneCapacity;
	
	private int maxNoOfPeople;
	
	private int maxNoOfBeds;
	
	private int noOfGarages;
	
	private boolean gardenAvailable;

	private Condition condition;

	private HeatingType heatingType;

	private double additionalCost;
	
	private double heatingCost;
	
	private boolean heatingCostIncluded;

	private double depositPeriod;

	private Date availableFrom;

	private boolean garageAvailable;

	private double garageCost;

	private String description;

	private String areaDescription;

	private String fittings;

	private boolean energyPassAvailable;

	private double energyRequirement;

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

	// erbpacht
	private boolean leasing;

	// GRZ
	private String coverageRatio;

	// GFZ
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

	private double powerSupplyCapacity;
	
	private double height;
	
	private boolean hasMultiPhaseElectricalSupply;

	private boolean hasCafeteria;

	private FlooringType flooringType;

	private boolean hasAirConidtion;

	private String nearestStation;

	private int timeToNearestPublicTransport;

	private int timeToAirportTravel;
	
	private double floorLoad;

	private TariffType tariffType = TariffType.Free;

	private boolean allowFullAddressDisplay;
	
	private boolean airConditioned;
		
	@GeoSpatialIndexed
	private Double[] location;

	private Date insertedTs;

	private Date updatedTs;

	private List<RealStateImage> images = new ArrayList<RealStateImage>();

	@Transient
	private String addressInfo;

	private ContactInfo contactInfo;

	private PaymentInfo paymentInfo;
	
	//RealState lifecycle statuses
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
	
	public double getEnergyRequirement() {
		return energyRequirement;
	}
	
	public void setEnergyRequirement(double energyRequirement) {
		this.energyRequirement = energyRequirement;
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

	public boolean isElevatorAvailable() {
		return elevatorAvailable;
	}

	public void setElevatorAvailable(boolean elevatorAvailable) {
		this.elevatorAvailable = elevatorAvailable;
	}

	public boolean isCommercialElevator() {
		return commercialElevator;
	}

	public void setCommercialElevator(boolean commercialElevator) {
		this.commercialElevator = commercialElevator;
	}

	public double getPersonalElevatorCapacity() {
		return personalElevatorCapacity;
	}

	public void setPersonalElevatorCapacity(double personalElevatorCapacity) {
		this.personalElevatorCapacity = personalElevatorCapacity;
	}

	public boolean isCraneAvailable() {
		return craneAvailable;
	}

	public void setCraneAvailable(boolean craneAvailable) {
		this.craneAvailable = craneAvailable;
	}

	public double getCraneCapacity() {
		return craneCapacity;
	}

	public void setCraneCapacity(double craneCapacity) {
		this.craneCapacity = craneCapacity;
	}

	public int getMaxNoOfPeople() {
		return maxNoOfPeople;
	}

	public void setMaxNoOfPeople(int maxNoOfPeople) {
		this.maxNoOfPeople = maxNoOfPeople;
	}

	public int getMaxNoOfBeds() {
		return maxNoOfBeds;
	}

	public void setMaxNoOfBeds(int maxNoOfBeds) {
		this.maxNoOfBeds = maxNoOfBeds;
	}

	public double getPowerSupplyCapacity() {
		return powerSupplyCapacity;
	}

	public void setPowerSupplyCapacity(double powerSupplyCapacity) {
		this.powerSupplyCapacity = powerSupplyCapacity;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
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

	public HeatingType getHeatingType() {
		return heatingType;
	}

	public void setHeatingType(HeatingType heatingType) {
		this.heatingType = heatingType;
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

	public double getHeatingCost() {
		return heatingCost;
	}

	public void setHeatingCost(double heatingCost) {
		this.heatingCost = heatingCost;
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

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
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

	public BuiltStatus getBuiltStatus() {
		return builtStatus;
	}

	public void setBuiltStatus(BuiltStatus builtStatus) {
		this.builtStatus = builtStatus;
	}

	public int getNoOfParkPlaces() {
		return noOfParkPlaces;
	}

	public void setNoOfParkPlaces(int noOfParkPlaces) {
		this.noOfParkPlaces = noOfParkPlaces;
	}

	public boolean isHasMultiPhaseElectricalSupply() {
		return hasMultiPhaseElectricalSupply;
	}

	public void setHasMultiPhaseElectricalSupply(boolean hasMultiPhaseElectricalSupply) {
		this.hasMultiPhaseElectricalSupply = hasMultiPhaseElectricalSupply;
	}

	public boolean isHasCafeteria() {
		return hasCafeteria;
	}

	public void setHasCafeteria(boolean hasCafeteria) {
		this.hasCafeteria = hasCafeteria;
	}

	public FlooringType getFlooringType() {
		return flooringType;
	}

	public void setFlooringType(FlooringType flooringType) {
		this.flooringType = flooringType;
	}

	public boolean isHasAirConidtion() {
		return hasAirConidtion;
	}

	public void setHasAirConidtion(boolean hasAirConidtion) {
		this.hasAirConidtion = hasAirConidtion;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public int getTimeToNearestPublicTransport() {
		return timeToNearestPublicTransport;
	}

	public void setTimeToNearestPublicTransport(int timeToNearestPublicTransport) {
		this.timeToNearestPublicTransport = timeToNearestPublicTransport;
	}

	public int getTimeToAirportTravel() {
		return timeToAirportTravel;
	}

	public void setTimeToAirportTravel(int timeToAirportTravel) {
		this.timeToAirportTravel = timeToAirportTravel;
	}

	public TariffType getTariffType() {
		return tariffType;
	}

	public void setTariffType(TariffType tariffType) {
		this.tariffType = tariffType;
	}

	public double getFloorLoad() {
		return floorLoad;
	}

	public void setFloorLoad(double floorLoad) {
		this.floorLoad = floorLoad;
	}
	
	public int getNoOfGarages() {
		return noOfGarages;
	}

	public void setNoOfGarages(int noOfGarages) {
		this.noOfGarages = noOfGarages;
	}
	
	private boolean hasValidSize(int size, TariffType t) {
		if (size < TariffType.getAllowdNoOfUploads(t)) {
			return true;
		} else {
			return false;
		}		
	}

	public boolean isAllowFullAddressDisplay() {
		return allowFullAddressDisplay;
	}

	public void setAllowFullAddressDisplay(boolean allowFullAddressDisplay) {
		this.allowFullAddressDisplay = allowFullAddressDisplay;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public double getSurroundingAreaSize() {
		return surroundingAreaSize;
	}

	public void setSurroundingAreaSize(double surroundingAreaSize) {
		this.surroundingAreaSize = surroundingAreaSize;
	}

	public boolean isAirConditioned() {
		return airConditioned;
	}

	public void setAirConditioned(boolean airConditioned) {
		this.airConditioned = airConditioned;
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

		if (this.isAllowFullAddressDisplay()) {
			addressInfo.append(this.getHouseNo());
			addressInfo.append(", ");
		} else {
			addressInfo.append(", ");
		}
		addressInfo.append(StringUtils.trimToEmpty(getAreaCode()));
		addressInfo.append(", ");
		addressInfo.append(StringUtils.trimToEmpty(getCity()));
		this.addressInfo = addressInfo.toString();
		return this.addressInfo;
	}

	/**
	 * Method checks if the tariff is valid based on Tariff Type
	 * 
	 * @return
	 */
	public boolean hasValidTariff() {
		int size = this.getNonTitleImages().size() + 1;
		return hasValidSize(size, this.getTariffType());
		
	}	
}
