package com.ecom.service.interfaces;

import java.io.InputStream;

import org.bson.types.ObjectId;

public interface ImageService {

    public void saveUploadedImageFileInDB(String originalFileName, InputStream in, ObjectId realStateId, boolean isTitle) ;
	
	public void deleteImage(ObjectId realStateId, ObjectId realStateImageId);
	
	public InputStream getImageAsBytes(String objectId) ;
}
