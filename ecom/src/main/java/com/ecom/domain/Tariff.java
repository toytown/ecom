package com.ecom.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Tariff implements Serializable {

	private ObjectId tariffId;
	private double price;
	private String tariffName;
	
}
