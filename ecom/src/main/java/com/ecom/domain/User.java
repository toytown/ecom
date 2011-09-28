package com.ecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	private Date insertTs;
	
	//private Set<Advertisement> advertisement =  new HashSet<Advertisement>(0);	
	
	//private List<ContactDetails> contactDetails =  new ArrayList<ContactDetails>(0);	
	
	private String activationCode;



		
}

