package com.ecom.service.interfaces;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.bson.types.ObjectId;

public interface ImageService {

	public void saveUploadedImageFile(FileUpload uploadedFile, ObjectId realStateId, boolean isTitleImage);
	
	public void deleteImage(ObjectId realStateId, ObjectId realStateImageId);
}
