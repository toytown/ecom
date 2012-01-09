package com.ecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = -66102433617339956L;

	// 1=standard 2=professional
	public static final 	List<Category> categories = new ArrayList<Category>();
	
	private static Category DEFAULT_CATEGORY = null;
	
	static {
		DEFAULT_CATEGORY = new Category("1", "Private");
		categories.add(DEFAULT_CATEGORY);
		categories.add(new Category("2", "Firmen"));		
		
	}		

	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}

	private String id;

	private String name;

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
	
	public static Category getDefaultCategory() {
		return DEFAULT_CATEGORY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
