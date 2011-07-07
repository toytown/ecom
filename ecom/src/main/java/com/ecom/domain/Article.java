package com.ecom.domain;

import java.math.BigDecimal;

import org.bson.types.ObjectId;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class Article {

	private ObjectId id;
	private String name;
	private String description;
	private int originalPrice;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	
}
