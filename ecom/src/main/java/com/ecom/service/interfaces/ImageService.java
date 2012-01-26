package com.ecom.service.interfaces;

import java.io.InputStream;

import com.ecom.domain.RealState;

public interface ImageService {

    public void saveUploadedImageFileInDB(String originalFileName, InputStream in, RealState realState, boolean isTitle) ;
	
	public void deleteImage(RealState realState, String realStateImageId);
	
	public InputStream getImageAsBytes(String objectId) ;
	
	public void deleteAllImages(RealState realState);
}
