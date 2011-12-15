package com.ecom.service.interfaces;

import java.io.InputStream;

import org.bson.types.ObjectId;

import com.ecom.domain.RealState;

public interface ImageService {

    public void saveUploadedImageFileInDB(String originalFileName, InputStream in, RealState realState, boolean isTitle) ;
	
	public void deleteImage(ObjectId realStateId, ObjectId realStateImageId);
	
	public InputStream getImageAsBytes(String objectId) ;
}
