package com.ecom.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RealState {

	@Id
	private ObjectId _id;
	private String title;
	private int status;
	private int originalPrice;
	private String type;

	private String areaCode;

	private String city;

	private String street;

	private String houseNo;

	private Double size;

	private Double cost;

	private Double floor;

	private Double totalFloors;

	private Double totalRooms;

	private Short bedRooms;

	private Short bathRooms;

	private Boolean toiletWithBathRoom;

	private Boolean cellarAvailable;

	private Boolean balconyAvailable;

	private Boolean liftAvailable;

	private Boolean gardenAvailable;

	private Short condition;

//	private CategoryType categoryType;

//	private HeatingType heatingType;

	private Double additionalCost;

	private Boolean heatingCostIncluded;

	private Double depositPeriod;

	private Date availableFrom;

	private Boolean garageAvailable;

	private Double garageCost;

	private String description;

	private String areaDescription;

	private String fittings;

	private Boolean energyPassAvailable;

	private Boolean kitchenAvailable;

	private Boolean furnished;

	private Boolean animalsAllowed;

	private Integer builtYear;

	private String lastRenovatedYear;

	private String otherInformation;

	private Boolean provisionFree;

	private String provisionCondition;

	private String imageDir;

	private Boolean barrierFree;

	private Boolean seniorAppartment;

	private User user;

//	private Category category;
	
}
