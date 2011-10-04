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

	private double size;

	private double cost;

	private double floor;

	private double totalFloors;

	private double totalRooms;

	private short bedRooms;

	private short bathRooms;

	private boolean toiletWithBathRoom;

	private boolean cellarAvailable;

	private boolean balconyAvailable;

	private boolean liftAvailable;

	private boolean gardenAvailable;

	private short condition;

//	private CategoryType categoryType;

//	private HeatingType heatingType;

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

	private Integer builtYear;

	private String lastRenovatedYear;

	private String otherInformation;

	private boolean provisionFree;

	private String provisionCondition;

	private String imageDir;

	private boolean barrierFree;

	private boolean seniorAppartment;

	private User user;

	private double[] location;
//	private Category category;
	
}
