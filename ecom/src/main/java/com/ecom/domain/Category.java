package com.ecom.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Category implements Serializable {

	private static final long serialVersionUID = 6330792452715523916L;

	@Id
	private ObjectId id;
	
	private String name;

//	private List<CategoryType> categoryTypes = new ArrayList<CategoryType>(0);;
	
}
