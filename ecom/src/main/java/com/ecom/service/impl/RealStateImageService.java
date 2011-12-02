package com.ecom.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Files;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateRepository;
import com.ecom.service.interfaces.ImageService;
import com.ecom.web.data.DetachableRealStateModel;

@Service("imageService")
public class RealStateImageService implements ImageService {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private RealStateRepository realStateRepository;

	private File imageStoreDir;

	public RealStateImageService() {
		super();
	}

	@Override
	public void saveUploadedImageFile(FileUpload uploadedFile, ObjectId realStateId, boolean isTitle) {

		this.imageStoreDir = appConfig.getImageStoreDir();
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

			RealState realState = new DetachableRealStateModel(realStateId).getObject();
			if (realState == null) {
				realState = new RealState();
				realState.setId(realStateId);
			}

			// creates a thumb nail title image for each uploaded image
			createResizedImage(originalImage, newResizedFile, true);
			RealStateImage thumbNailImage = new RealStateImage();
			ObjectId idThumbNail = new ObjectId();
			thumbNailImage.setId(idThumbNail.toString());
			thumbNailImage.setRealStateId(realStateId.toString());
			thumbNailImage.setMimeType(mimeType);
			thumbNailImage.setSize(size);
			thumbNailImage.setImageFileName(clientFileName);
			thumbNailImage.setTitleImage(isTitle);
			thumbNailImage.setThumbNail(true);
			realState.getImages().add(thumbNailImage);

			// creates a corresponding image
			RealStateImage image = new RealStateImage();
			createResizedImage(originalImage, newResizedFile, false);
			ObjectId idImage = new ObjectId();
			image.setId(idImage.toString());
			image.setRealStateId(realStateId.toString());
			image.setMimeType(mimeType);
			image.setSize(size);
			image.setImageFileName(clientFileName);
			image.setTitleImage(isTitle);
			image.setThumbNail(false);
			realState.getImages().add(image);
			
			realStateRepository.save(realState);

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
	protected void createResizedImage(BufferedImage image, File resizedImageFile, boolean isThumbNail) throws IOException {
		if (isThumbNail) {
			image = ImageUtils.resize(image, 120, 90);
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

	@Override
	public void deleteImage(ObjectId realStateId, ObjectId realStateImageId) {
		RealState realState = realStateRepository.findOne(realStateId);

		if (!realState.getImages().isEmpty()) {
			Iterator<RealStateImage> iter = realState.getImages().iterator();

			while (iter.hasNext()) {
				RealStateImage img = iter.next();
				if (realStateImageId != null && img != null && img.getId().equals(realStateImageId.toString())) {
					iter.remove();
					realStateRepository.save(realState);
				}
			}
		}

	}

}
