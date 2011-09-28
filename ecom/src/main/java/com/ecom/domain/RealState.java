package com.ecom.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RealState {

	@Id
	private ObjectId _id;
	private String title;
	private Short status;
	private String description;
	private int originalPrice;
	
}
