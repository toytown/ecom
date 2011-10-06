package com.ecom.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = -3721902561262002897L;

	private Long id;

	private String title;

	private String firstName;

	private String lastName;

	private String companyName;

	private Short userCategory;

	private Short userStatus;

	private String userName;

	private String password;

	private String status;

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

	private Short contactStatus;
	
	private Date insertTs;
	
	private Date updatedTs;	
}
