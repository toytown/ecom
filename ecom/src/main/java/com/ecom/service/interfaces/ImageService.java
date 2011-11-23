package com.ecom.service.interfaces;

import org.apache.wicket.markup.html.form.upload.FileUpload;

public interface ImageService {

	public void saveUploadedImageFile(FileUpload uploadedFile, String realStateId, boolean isTitle);
	
}
