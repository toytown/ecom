package com.ecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -3721902561262002897L;
			
	@Id
	private ObjectId id;
	
	private String title;

	private String firstName;

	private String lastName;

	private String companyName;

	private String userName;

	private String password;

	@Transient
	private String password2;
	
	//inactive
	private int status = 0; 

	private String activationCode;

	private String city;

	private String zip;

	private String street;

	private String houseNumber;

	private String countryCode;

	private String email;

	private String fax;

	private String mobile;

	private String phone1;

	private String phone2;

	private String homePageURL;

	private int contactStatus;

	private Date insertTs;

	private Date updatedTs;

	private Date lastLoginTs;
	
	private Date activationTs;
	
	private int userType;
	
	private List<SearchRequest> searchRequests = new ArrayList<SearchRequest>();
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

	public int getContactStatus() {
		return contactStatus;
	}

	public void setContactStatus(int contactStatus) {
		this.contactStatus = contactStatus;
	}

	public Date getInsertTs() {
		return insertTs;
	}

	public void setInsertTs(Date insertTs) {
		this.insertTs = insertTs;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	
	public final void activate() {
		setStatus(Integer.valueOf(UserStatus.ACTIVE.toString()));		
	}
	
	public final void suspend() {
		setStatus(Integer.valueOf(UserStatus.SUSPENDED.toString()));		
	}	
	
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public void activateUser() {
   	 this.setStatus(UserStatus.getStatusId(UserStatus.ACTIVE));
   	 this.setActivationTs(Calendar.getInstance().getTime());
    }
    
    public boolean isActive() {
        if (this.getStatus() == UserStatus.getStatusId(UserStatus.ACTIVE)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isInactive() {
        if (this.getStatus() == UserStatus.getStatusId(UserStatus.INACTIVE)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void deActivateUser() {
   	 this.setStatus(UserStatus.getStatusId(UserStatus.INACTIVE));
   	 this.setActivationTs(Calendar.getInstance().getTime());
    }
    
    public boolean changePassword(String newPassword1, String newPassword2, String oldPassword) {
   	 
   	 if (!StringUtils.isEmpty(newPassword1) && !StringUtils.isEmpty(newPassword2)) {
   		 if (!oldPassword.equals(newPassword1) && newPassword1.equals(newPassword2)) {
   			 setPassword(newPassword1);
   			 return true;
   		 } else {
   			 return false;
   		 }
   	 }
   	 
   	 return false;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activationCode == null) ? 0 : activationCode.hashCode());
		result = prime * result + ((activationTs == null) ? 0 : activationTs.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + contactStatus;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((homePageURL == null) ? 0 : homePageURL.hashCode());
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((insertTs == null) ? 0 : insertTs.hashCode());
		result = prime * result + ((lastLoginTs == null) ? 0 : lastLoginTs.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((password2 == null) ? 0 : password2.hashCode());
		result = prime * result + ((phone1 == null) ? 0 : phone1.hashCode());
		result = prime * result + ((phone2 == null) ? 0 : phone2.hashCode());
		result = prime * result + ((searchRequests == null) ? 0 : searchRequests.hashCode());
		result = prime * result + status;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((updatedTs == null) ? 0 : updatedTs.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		User other = (User) obj;
		if (activationCode == null) {
			if (other.activationCode != null)
				return false;
		} else if (!activationCode.equals(other.activationCode))
			return false;
		if (activationTs == null) {
			if (other.activationTs != null)
				return false;
		} else if (!activationTs.equals(other.activationTs))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (contactStatus != other.contactStatus)
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homePageURL == null) {
			if (other.homePageURL != null)
				return false;
		} else if (!homePageURL.equals(other.homePageURL))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (insertTs == null) {
			if (other.insertTs != null)
				return false;
		} else if (!insertTs.equals(other.insertTs))
			return false;
		if (lastLoginTs == null) {
			if (other.lastLoginTs != null)
				return false;
		} else if (!lastLoginTs.equals(other.lastLoginTs))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (password2 == null) {
			if (other.password2 != null)
				return false;
		} else if (!password2.equals(other.password2))
			return false;
		if (phone1 == null) {
			if (other.phone1 != null)
				return false;
		} else if (!phone1.equals(other.phone1))
			return false;
		if (phone2 == null) {
			if (other.phone2 != null)
				return false;
		} else if (!phone2.equals(other.phone2))
			return false;
		if (searchRequests == null) {
			if (other.searchRequests != null)
				return false;
		} else if (!searchRequests.equals(other.searchRequests))
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
		if (updatedTs == null) {
			if (other.updatedTs != null)
				return false;
		} else if (!updatedTs.equals(other.updatedTs))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	public Date getActivationTs() {
		return activationTs;
	}

	public void setActivationTs(Date activationTs) {
		this.activationTs = activationTs;
	}

	public List<SearchRequest> getSearchRequests() {
		return searchRequests;
	}

	public void setSearchRequests(List<SearchRequest> searchRequests) {
		this.searchRequests = searchRequests;
	}

	public Date getLastLoginTs() {
		return lastLoginTs;
	}

	public void setLastLoginTs(Date lastLoginTs) {
		this.lastLoginTs = lastLoginTs;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
}
