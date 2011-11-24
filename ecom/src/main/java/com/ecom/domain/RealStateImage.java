package com.ecom.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class RealStateImage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;

	private String imageFileName;
	
	private long size;
	
	private int width;
	
	private int height;
	
	private int lastUpdated;
	
	private String mimeType;
	
	private String realStateId;
	
	private boolean isTitleImage;
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(int lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getRealStateId() {
		return realStateId;
	}

	public void setRealStateId(String realStateId) {
		this.realStateId = realStateId;
	}
	
	public String getImageURL() {
		return "http://localhost/image-repository/" + getRealStateId() + "/" + getImageFileName();
	}

    public boolean isTitleImage() {
        return isTitleImage;
    }

    public void setTitleImage(boolean isTitleImage) {
        this.isTitleImage = isTitleImage;
    }
}
