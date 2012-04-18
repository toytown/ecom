package com.ecom.domain;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecom.common.utils.GeoLocationUtils;

@Document(collection = "realstate")
public class RealState implements Serializable {

	private static final long serialVersionUID = 4910191493640273023L;

	@Id
	private ObjectId id;

	private String title;

	private int status;

	private int originalPrice;

	//offer type - Rent, Buy
	private int typeId;

	//type of real state - House, Appartment
	private int realStateType;
	
	@Indexed
	private String areaCode;

	@Indexed
	private String city;

	private String street;

	private String houseNo;

	@Indexed
	private double size;

	@Indexed
	private double cost;

	private double floor;

	private double totalFloors;

	@Indexed
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

	@Indexed
	private boolean energyPassAvailable;

	@Indexed
	private boolean kitchenAvailable;

	@Indexed
	private boolean furnished;

	@Indexed
	private boolean animalsAllowed;

	@Indexed
	private int builtYear;

	@Indexed
	private String lastRenovatedYear;

	private String otherInformation;
	
	@Indexed
	private boolean provisionFree;

	@Indexed
	private String provisionCondition;

	
	private String imageLocation;

	private boolean barrierFree;

	private boolean seniorAppartment;

	private String userId;

	private Date activationDate;
	   
	private Date insertedTs;

	private Date updatedTs;

	@GeoSpatialIndexed
	private Double[] location;
	
	private List<RealStateImage> images = new ArrayList<RealStateImage>();
	
	public enum STATUS {
	        NEW, VALID, ACTIVE, INACTIVE;
	        
	        public static int getValue(STATUS status) {
	            
	            switch(status) {
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

        //removes previously stored title images
        while(iter.hasNext()) {
            RealStateImage img = iter.next();
            if (img.isTitleImage()) {
                iter.remove();
            }
        }
       this.getImages().addAll(titlImages);
    }
    
	public String getTitleImageLocation(String imageRepository) {
		try {
			URL url =  new URL("http://localhost/image-repository" + "/" + getId() + "/" + getTitleThumbNailImage());
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
    
	public int getRealStateType() {
		return realStateType;
	}

	public void setRealStateType(int realStateType) {
		this.realStateType = realStateType;
	}

	public boolean isValid() {
	    
	    if (StringUtils.isEmpty(getTitle())) {
	        return false;
	    } 
	    
	    if (StringUtils.isEmpty(getDescription())) {
	        return false;
	    }
	    
	    if(StringUtils.isEmpty(this.getStreet())) {
	        return false;
	    }
	    
        if(StringUtils.isEmpty(this.getCity())) {
            return false;
        }
        
        if(StringUtils.isEmpty(this.getAreaCode())) {
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
		
	    if (STATUS.getValue(STATUS.VALID)  == this.getStatus()) {
	   	 this.setActivationDate(activationDate);
	       this.setStatus(STATUS.getValue(STATUS.ACTIVE));
	    }
	}
	
	public String getAddress() {
	
	    StringBuilder address = new StringBuilder();
	    
        if (StringUtils.isNotEmpty(this.getAreaCode()) && this.getAreaCode().trim().length() >= 2) {
            address.append(this.getAreaCode());
        }

        
	    if (StringUtils.isNotEmpty(this.getCity()) && this.getCity().trim().length() >= 2) {
	        address.append(this.getCity());
	    }
	    
	    if (StringUtils.isNotEmpty(this.getStreet()) && this.getStreet().trim().length() >= 2) {
	        address.append(", ");
            address.append(this.getStreet());

            if (StringUtils.isNotEmpty(this.getHouseNo())) {
                address.append(" ");
                address.append(this.getHouseNo());
            }
        }
	    
        
	    return address.toString();
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
        result = prime * result + ((areaCode == null) ? 0 : areaCode.hashCode());
        result = prime * result + ((areaDescription == null) ? 0 : areaDescription.hashCode());
        result = prime * result + ((availableFrom == null) ? 0 : availableFrom.hashCode());
        result = prime * result + (balconyAvailable ? 1231 : 1237);
        result = prime * result + (barrierFree ? 1231 : 1237);
        result = prime * result + bathRooms;
        result = prime * result + bedRooms;
        result = prime * result + builtYear;
        result = prime * result + categoryId;
        result = prime * result + (cellarAvailable ? 1231 : 1237);
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + condition;
        temp = Double.doubleToLongBits(cost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(depositPeriod);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (energyPassAvailable ? 1231 : 1237);
        result = prime * result + ((fittings == null) ? 0 : fittings.hashCode());
        temp = Double.doubleToLongBits(floor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (furnished ? 1231 : 1237);
        result = prime * result + (garageAvailable ? 1231 : 1237);
        temp = Double.doubleToLongBits(garageCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (gardenAvailable ? 1231 : 1237);
        result = prime * result + (heatingCostIncluded ? 1231 : 1237);
        result = prime * result + heatingTypeId;
        result = prime * result + ((houseNo == null) ? 0 : houseNo.hashCode());
        result = prime * result + ((imageLocation == null) ? 0 : imageLocation.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((insertedTs == null) ? 0 : insertedTs.hashCode());
        result = prime * result + (kitchenAvailable ? 1231 : 1237);
        result = prime * result + ((lastRenovatedYear == null) ? 0 : lastRenovatedYear.hashCode());
        result = prime * result + (liftAvailable ? 1231 : 1237);
        result = prime * result + originalPrice;
        result = prime * result + ((otherInformation == null) ? 0 : otherInformation.hashCode());
        result = prime * result + ((provisionCondition == null) ? 0 : provisionCondition.hashCode());
        result = prime * result + (provisionFree ? 1231 : 1237);
        result = prime * result + realStateType;
        result = prime * result + (seniorAppartment ? 1231 : 1237);
        temp = Double.doubleToLongBits(size);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + status;
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + (toiletWithBathRoom ? 1231 : 1237);
        temp = Double.doubleToLongBits(totalFloors);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalRooms);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + typeId;
        result = prime * result + ((updatedTs == null) ? 0 : updatedTs.hashCode());
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
        if (builtYear != other.builtYear)
            return false;
        if (categoryId != other.categoryId)
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
        if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
            return false;
        if (Double.doubleToLongBits(depositPeriod) != Double.doubleToLongBits(other.depositPeriod))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
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
        if (furnished != other.furnished)
            return false;
        if (garageAvailable != other.garageAvailable)
            return false;
        if (Double.doubleToLongBits(garageCost) != Double.doubleToLongBits(other.garageCost))
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
        if (imageLocation == null) {
            if (other.imageLocation != null)
                return false;
        } else if (!imageLocation.equals(other.imageLocation))
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
        if (kitchenAvailable != other.kitchenAvailable)
            return false;
        if (lastRenovatedYear == null) {
            if (other.lastRenovatedYear != null)
                return false;
        } else if (!lastRenovatedYear.equals(other.lastRenovatedYear))
            return false;
        if (liftAvailable != other.liftAvailable)
            return false;
        if (originalPrice != other.originalPrice)
            return false;
        if (otherInformation == null) {
            if (other.otherInformation != null)
                return false;
        } else if (!otherInformation.equals(other.otherInformation))
            return false;
        if (provisionCondition == null) {
            if (other.provisionCondition != null)
                return false;
        } else if (!provisionCondition.equals(other.provisionCondition))
            return false;
        if (provisionFree != other.provisionFree)
            return false;
        if (realStateType != other.realStateType)
            return false;
        if (seniorAppartment != other.seniorAppartment)
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
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (toiletWithBathRoom != other.toiletWithBathRoom)
            return false;
        if (Double.doubleToLongBits(totalFloors) != Double.doubleToLongBits(other.totalFloors))
            return false;
        if (Double.doubleToLongBits(totalRooms) != Double.doubleToLongBits(other.totalRooms))
            return false;
        if (typeId != other.typeId)
            return false;
        if (updatedTs == null) {
            if (other.updatedTs != null)
                return false;
        } else if (!updatedTs.equals(other.updatedTs))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
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
}
