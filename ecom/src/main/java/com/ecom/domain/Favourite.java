package com.ecom.domain;

import java.io.Serializable;
import java.util.List;

public class Favourite implements Serializable{

	private static final long serialVersionUID = -608362754868623213L;

	private String id;
	
	private String name;
	
	private List<String> realStateIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRealStateIds() {
		return realStateIds;
	}

	public void setRealStateIds(List<String> realStateIds) {
		this.realStateIds = realStateIds;
	}

	
}
