package com.ecom.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateImageRepository;
import com.ecom.service.interfaces.ImageService;

@Service("imageService")
public class RealStateImageService implements ImageService {
	
	@Autowired
	private AppConfig appConfig;
	
   @SpringBean
   private RealStateImageRepository imageRepository;
   
   private File imageStoreDir;
   
   public RealStateImageService() {
		super();
		this.imageStoreDir = appConfig.getImageStoreDir();
	}

	public void saveUploadedImageFile(FileUpload uploadedFile, String realStateId, boolean isTitle) {

       if (uploadedFile == null || uploadedFile.getSize() == 0l) {
           return;
       }

       // Create a new file
       String mimeType = uploadedFile.getContentType();
       String clientFileName = uploadedFile.getClientFileName();
       long size = uploadedFile.getSize();

       File uploadDir = new File(imageStoreDir + File.separator + realStateId);
       File uploadDirOriginalImages = new File(imageStoreDir + File.separator + realStateId + "/tmp");

       if (!uploadDir.exists()) {
           uploadDir.mkdirs();
       }

       if (!uploadDirOriginalImages.exists()) {
           uploadDirOriginalImages.mkdirs();
       }

       File newFile = new File(uploadDirOriginalImages, clientFileName);

       // Check new file, delete if it already existed
       checkFileExists(newFile);

       try {
           // Save to new file
           newFile.createNewFile();
           uploadedFile.writeTo(newFile);

           BufferedImage originalImage = ImageIO.read(newFile);

           File newResizedFile = new File(uploadDir, clientFileName);
           
           //[TODO check content type before resizing]
           createResizedImage(originalImage, newResizedFile, true);

           RealStateImage image = new RealStateImage();
           image.setId(new ObjectId());
           image.setRealStateId(realStateId);
           image.setMimeType(mimeType);
           image.setSize(size);
           image.setImageFileName(clientFileName);
           imageRepository.save(image);

       } catch (Exception e) {
           e.printStackTrace();
           throw new IllegalStateException("Unable to write file");
       }

   }

   /**
    * Check whether the file already exists, and if so, try to delete it.
    * 
    * @param newFile
    *           the file to check
    */
   private void checkFileExists(File newFile) {
       if (newFile.exists()) {
           // Try to delete the file
           if (!Files.remove(newFile)) {
               throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
           }
       }
   }

   /**
    * Resizes the uploaded image because uploaded images could be large and size
    * could be different
    * 
    * @param image
    * @param appartmentId
    * @param fileName
    * @param isTitle
    * @throws IOException
    */
   protected void createResizedImage(BufferedImage image, File resizedImageFile, boolean isTitle) throws IOException {
       if (isTitle) {
           image = ImageUtils.resize(image, 95, 95);
       } else {
           image = ImageUtils.resize(image, 480, 367);
       }

       ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
       ImageIO.write(image, "jpeg", baos);
       baos.flush();
       byte[] imageBytes = baos.toByteArray();

       if (resizedImageFile.createNewFile()) {
           FileOutputStream fos = new FileOutputStream(resizedImageFile);
           fos.write(imageBytes);
           fos.flush();
           fos.close();

       }
       baos.close();
   }
}	

