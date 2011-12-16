package com.ecom.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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
    private RealStateRepository realStateRepository;

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    private GridFS gridFS;

    public RealStateImageService() {
        super();
    }

    @PostConstruct
    public void postInitialize() {
        this.gridFS = new GridFS(mongoTemplate.getDb(), "realStateImages");
    }


    @Override
    public void saveUploadedImageFileInDB(String originalFileName, InputStream in, RealState realState, boolean isTitle) {

        try {

            BufferedImage originalImage = ImageIO.read(in);

            // creates a thumb nail title image for each uploaded image
            RealStateImage thumbNailImage = new RealStateImage();
            byte[] thumNailImgBytes = createResizedImage(originalImage, true);
            GridFSInputFile gridInputThumImgFile = this.gridFS.createFile(thumNailImgBytes);
            thumbNailImage.setId(gridInputThumImgFile.getId().toString());
            thumbNailImage.setRealStateId(realState.getId().toString());
            thumbNailImage.setMimeType(gridInputThumImgFile.getContentType());
            thumbNailImage.setSize(gridInputThumImgFile.getLength());
            thumbNailImage.setImageFileName(gridInputThumImgFile.getFilename());
            thumbNailImage.setTitleImage(isTitle);
            thumbNailImage.setThumbNail(true);
            realState.getImages().add(thumbNailImage);

            // creates a corresponding image
            RealStateImage image = new RealStateImage();
            byte[] imageBytes = createResizedImage(originalImage, false);
            GridFSInputFile gridInputImgFile = this.gridFS.createFile(imageBytes);
            image.setId(gridInputImgFile.getId().toString());
            image.setRealStateId(realState.getId().toString());
            image.setMimeType(gridInputImgFile.getContentType());
            image.setSize(gridInputImgFile.getLength());
            image.setImageFileName(gridInputImgFile.getFilename());
            image.setTitleImage(isTitle);
            image.setThumbNail(false);

            realState.getImages().add(image);
            gridInputThumImgFile.setFilename(originalFileName);
            gridInputThumImgFile.save();

            gridInputImgFile.setFilename(originalFileName);
            gridInputImgFile.save();
            realStateRepository.save(realState);

        } catch (Exception e) {
            throw new RuntimeException("Unable to write file " + e.getMessage());
        }

    }

    public InputStream getImageAsBytes(String objectId) {
        GridFSDBFile imgDBFile = this.gridFS.find(new ObjectId(objectId));

        return imgDBFile.getInputStream();

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
    protected byte[] createResizedImage(BufferedImage image, boolean isThumbNail) throws IOException {
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
        return imageBytes;
    }

    @Override
    public void deleteImage(RealState realState, String realStateImageId) {

        if (realState != null && !realState.getImages().isEmpty()) {
            Iterator<RealStateImage> iter = realState.getImages().iterator();

            while (iter.hasNext()) {
                RealStateImage img = iter.next();
                if (realStateImageId != null && img != null && img.getId().equals(realStateImageId)) {
                    
                    if (this.gridFS.findOne(realStateImageId) != null) {
                        this.gridFS.remove(realStateImageId);
                        iter.remove();
                    }
                    realStateRepository.save(realState);
                }
            }
        }

    }

}
