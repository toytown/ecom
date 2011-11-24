package com.ecom.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
import com.ecom.repository.RealStateImageRepository;
import com.ecom.repository.RealStateRepository;
import com.ecom.service.interfaces.ImageService;
import com.ecom.web.data.DetachableRealStateModel;

@Service("imageService")
public class RealStateImageService implements ImageService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RealStateImageRepository imageRepository;

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

            
            if (isTitle) {
                //[TODO check content type before resizing]
                createResizedImage(originalImage, newResizedFile, true);
                RealState realState = new DetachableRealStateModel(realStateId.toString()).getObject();
                if (realState != null) {
                    realState.setTitleImage(uploadedFile.getClientFileName());
                    realStateRepository.save(realState);
                } else {
                    realState = new RealState();
                    realState.setId(realStateId);
                    realState.setTitleImage(uploadedFile.getClientFileName());
                    realStateRepository.save(realState);
                }
                
                //creates a corresponding title image
                createResizedImage(originalImage, newResizedFile, false);
                RealStateImage image = new RealStateImage();
                image.setId(new ObjectId());
                image.setRealStateId(realStateId.toString());
                image.setMimeType(mimeType);
                image.setSize(size);
                image.setImageFileName(clientFileName);
                image.setTitleImage(true);
                imageRepository.save(image);
            } else {
                createResizedImage(originalImage, newResizedFile, false);
                RealStateImage image = new RealStateImage();
                image.setId(new ObjectId());
                image.setRealStateId(realStateId.toString());
                image.setMimeType(mimeType);
                image.setSize(size);
                image.setImageFileName(clientFileName);
                imageRepository.save(image);
                
            }
            


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

    @Override
    public void deleteImage(ObjectId id, boolean isTitleImage) {
        if (isTitleImage) {
            RealState realState = realStateRepository.findOne(id);
            
            if (realState != null) {
                realState.setTitleImage("");
                realStateRepository.save(realState);
            }
        } else {
            RealStateImage realStateImage = imageRepository.findOne(id);
            
            if (realStateImage != null) {
                imageRepository.save(realStateImage);
            }
        }

    }
}
