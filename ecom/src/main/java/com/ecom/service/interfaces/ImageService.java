package com.ecom.service.interfaces;

import java.io.File;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.bson.types.ObjectId;

public interface ImageService {

    public void saveUploadedImageFileInDB(File newFile, ObjectId realStateId, boolean isTitle) ;
    
    public File createUploadedFileInFileSystem(FileUpload uploadedFile, ObjectId realStateId);
	
	public void deleteImage(ObjectId realStateId, ObjectId realStateImageId);
	
	public byte[] getImageAsBytes(String objectId) throws Exception;
}
