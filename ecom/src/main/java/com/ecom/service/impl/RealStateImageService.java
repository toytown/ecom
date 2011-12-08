package com.ecom.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Files;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateRepository;
import com.ecom.service.interfaces.ImageService;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service("imageService")
public class RealStateImageService implements ImageService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RealStateRepository realStateRepository;

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    private File imageStoreDir;

    private GridFS gridFS;

    public RealStateImageService() {
        super();

    }

    public File createUploadedFileInFileSystem(FileUpload uploadedFile, ObjectId realStateId) {
        if (uploadedFile == null || uploadedFile.getSize() == 0l) {
            return null;
        }

        // Create a new file
        // String mimeType = uploadedFile.getContentType();
        String clientFileName = uploadedFile.getClientFileName();
        // long size = uploadedFile.getSize();

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
            newFile.createNewFile();
            uploadedFile.writeTo(newFile);
            
            return newFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveUploadedImageFileInDB(File newFile, ObjectId realStateId, boolean isTitle) {
        this.gridFS = new GridFS(mongoTemplate.getDb(), "realStateImages");

        this.imageStoreDir = appConfig.getImageStoreDir();
        File uploadDir = new File(imageStoreDir + File.separator + realStateId);
        
        try {
            
            BufferedImage originalImage = ImageIO.read(newFile);

            File originalUploadedFile = new File(uploadDir, newFile.getName());

            RealState realState = realStateRepository.findOne(realStateId);
            if (realState == null) {
                realState = new RealState();
                realState.setId(realStateId);
            }

            // creates a thumb nail title image for each uploaded image
            RealStateImage thumbNailImage = new RealStateImage();
            byte[] thumNailImgBytes = createResizedImage(originalImage, originalUploadedFile, true);
            GridFSInputFile gridInputThumImgFile = this.gridFS.createFile(thumNailImgBytes);
            thumbNailImage.setId(gridInputThumImgFile.getId().toString());
            thumbNailImage.setRealStateId(realStateId.toString());
            thumbNailImage.setMimeType(gridInputThumImgFile.getContentType());
            thumbNailImage.setSize(gridInputThumImgFile.getLength());
            thumbNailImage.setImageFileName(gridInputThumImgFile.getFilename());
            thumbNailImage.setTitleImage(isTitle);
            thumbNailImage.setThumbNail(true);
            realState.getImages().add(thumbNailImage);

            // creates a corresponding image
            RealStateImage image = new RealStateImage();
            byte[] imageBytes = createResizedImage(originalImage, originalUploadedFile, false);
            GridFSInputFile gridInputImgFile = this.gridFS.createFile(imageBytes);
            image.setId(gridInputImgFile.getId().toString());
            image.setRealStateId(realStateId.toString());
            image.setMimeType(gridInputImgFile.getContentType());
            image.setSize(gridInputImgFile.getLength());
            image.setImageFileName(gridInputImgFile.getFilename());
            image.setTitleImage(isTitle);
            image.setThumbNail(false);
            
            realState.getImages().add(image);
            gridInputThumImgFile.setFilename(originalUploadedFile.getName());
            gridInputThumImgFile.save();
            
            gridInputImgFile.setFilename(originalUploadedFile.getName());
            gridInputImgFile.save();
            realStateRepository.save(realState);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Unable to write file");
        }

    }

    public byte[] getImageAsBytes(String objectId) throws Exception {
        GridFSDBFile imgDBFile = this.gridFS.find(new ObjectId(objectId));
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        DigestInputStream is = new DigestInputStream(imgDBFile.getInputStream(), md5);

        while (is.read() >= 0) {

            int r = is.read(new byte[2048]);
            if (r < 0)
                break;

        }
        byte[] digest = md5.digest();
        return digest;
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
    protected byte[] createResizedImage(BufferedImage image, File resizedImageFile, boolean isThumbNail) throws IOException {
        if (isThumbNail) {
            image = ImageUtils.resize(image, 120, 90);
        } else {
            image = ImageUtils.resize(image, 480, 367);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
        ImageIO.write(image, "jpeg", baos);
        baos.flush();

        byte[] imageBytes = baos.toByteArray();
        baos.close();

        if (resizedImageFile.createNewFile()) {
            FileOutputStream fos = new FileOutputStream(resizedImageFile);
            fos.write(imageBytes);
            fos.flush();
            fos.close();
        }

        return imageBytes;
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
                    if (this.gridFS.findOne(realStateImageId) != null) {
                        this.gridFS.remove(realStateImageId);
                    }
                    realStateRepository.save(realState);
                }
            }
        }

    }

}
