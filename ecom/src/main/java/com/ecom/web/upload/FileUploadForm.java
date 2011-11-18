package com.ecom.web.upload;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.MultiFileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.lang.Bytes;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateImageRepository;
import com.ecom.repository.RealStateRepository;

public class FileUploadForm extends Form<String> {

    private static final long serialVersionUID = -8486596461194196986L;

    private final Collection<FileUpload> uploads = new ArrayList<FileUpload>();
    private static final Logger logger = Logger.getLogger(FileUploadForm.class);
    
    private File imageStoreDir;
    
    @SpringBean
    private AppConfig appConfig;
    
    @SpringBean
    private RealStateRepository realStateRepository;
    
    @SpringBean
    private RealStateImageRepository imageRepository;
    
    public FileUploadForm(String name, IModel<String> realStateIdModel) {
        super(name, realStateIdModel);
        Injector.get().inject(this);
        
        imageStoreDir = appConfig.getImageStoreDir();
        
        // set this form to multi-part mode (always needed for uploads!)
        setMultiPart(true);

        // Add one multi-file upload field
        add(new MultiFileUploadField("fileInput", new PropertyModel<Collection<FileUpload>>(this, "uploads"), 5));
        
        // Set maximum size to 5000K 
        setMaxSize(Bytes.kilobytes(5000));
        imageStoreDir = appConfig.getImageStoreDir();
    }

    /**
     * @see org.apache.wicket.markup.html.form.Form#onSubmit()
     */
    @Override
    protected void onSubmit() {
        for (FileUpload upload : uploads) {

            // Create a new file
            String mimeType = upload.getContentType();
            String clientFileName = upload.getClientFileName();
            long size = upload.getSize();

            
            //id of the apartment for which the images are uploaded
            String realStateId = (String) this.getDefaultModel().getObject();
            
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
                upload.writeTo(newFile);
                
                BufferedImage originalImage = ImageIO.read(newFile);                

                File newResizedFile = new File(uploadDir, clientFileName);
                createResizedImage(originalImage, newResizedFile, false);
                
                RealStateImage image = new RealStateImage();
                image.setRealStateId(realStateId);
                image.setMimeType(mimeType);
                image.setSize(size);
                image.setImageFileName(clientFileName);
                imageRepository.save(image);
                
            } catch (Exception e) {
                throw new IllegalStateException("Unable to write file");
            }
        }

        //		Iterator<FileUpload> it = uploads.iterator();

        //		// Create a new file
        //		OASSession session = (OASSession) OASSession.get();
        //		boolean setFirstImageAsTitle = false;
        //		Advertisement appartment = this.getModelObject();
        //		appartment.getImages().removeAll(appartment.getImages());
        //		
        //		while (it.hasNext()) {
        //			FileUpload upload = it.next();
        //			if (!setFirstImageAsTitle) {
        //				appartment.setTitleImage(upload.getClientFileName());
        //				setFirstImageAsTitle = true;
        //			}
        //			Images images = new Images();
        //			images.setImageName(upload.getClientFileName());
        //			images.setAdvertisement(appartment);
        //			appartment.getImages().add(images);
        //			writeImageToFile(appartment, upload);
        //
        //		}
        //		appartment.setInsertDate(new Timestamp(System.currentTimeMillis()));
        //		appartment.setUser(session.getUser());
        //		appartment.setInsertedBy(session.getUser().getUserName());		
        //		appartment = advertisementSearchService.update(appartment);
    }


    /**
     * Check whether the file already exists, and if so, try to delete it.
     * 
     * @param newFile
     *            the file to check
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
     * Resizes the uploaded image because uploaded images could be large and size could be different
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
