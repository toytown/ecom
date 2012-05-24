package com.ecom.domain;

import java.io.Serializable;

public class RealStateImage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String imageFileName;
	
	private long size;
	
	private int width;
	
	private int height;
	
	private int lastUpdated;
	
	private String mimeType;
	
	private String realStateId;
	
	private boolean isTitleImage;
	
	private boolean isThumbNail;
	
	private boolean isImageAppartmentPlan;
	
	public boolean isImageAppartmentPlan() {
		return isImageAppartmentPlan;
	}

	public void setImageAppartmentPlan(boolean isImageAppartmentPlan) {
		this.isImageAppartmentPlan = isImageAppartmentPlan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

    public boolean isThumbNail() {
        return isThumbNail;
    }

    public void setThumbNail(boolean isThumbNail) {
        this.isThumbNail = isThumbNail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + ((imageFileName == null) ? 0 : imageFileName.hashCode());
        result = prime * result + (isThumbNail ? 1231 : 1237);
        result = prime * result + (isTitleImage ? 1231 : 1237);
        result = prime * result + lastUpdated;
        result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
        result = prime * result + ((realStateId == null) ? 0 : realStateId.hashCode());
        result = prime * result + (int) (size ^ (size >>> 32));
        result = prime * result + width;
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
        RealStateImage other = (RealStateImage) obj;
        if (height != other.height)
            return false;
        if (imageFileName == null) {
            if (other.imageFileName != null)
                return false;
        } else if (!imageFileName.equals(other.imageFileName))
            return false;
        if (isThumbNail != other.isThumbNail)
            return false;
        if (isTitleImage != other.isTitleImage)
            return false;
        if (lastUpdated != other.lastUpdated)
            return false;
        if (mimeType == null) {
            if (other.mimeType != null)
                return false;
        } else if (!mimeType.equals(other.mimeType))
            return false;
        if (realStateId == null) {
            if (other.realStateId != null)
                return false;
        } else if (!realStateId.equals(other.realStateId))
            return false;
        if (size != other.size)
            return false;
        if (width != other.width)
            return false;
        return true;
    }
}
