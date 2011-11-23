package com.ecom.web.upload;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.bson.types.ObjectId;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateImageRepository;
import com.ecom.repository.RealStateRepository;
import com.ecom.web.components.buttons.WepJsButton;
import com.ecom.web.data.DetachableRealStateModel;
import com.ecom.web.main.GenericTemplatePage;

public class TitleImageUploadPage extends GenericTemplatePage {

	private static final long serialVersionUID = 1942366724719884862L;

	private Form<ObjectId> titleImageUploadForm = null;

    @SpringBean
    private AppConfig appConfig;

    @SpringBean
    private RealStateImageRepository imageRepository;

    @SpringBean
    private RealStateRepository realStateRepository;
    
    private File imageStoreDir;

    private final FileUploadField uploadTitleField ;
    
    public TitleImageUploadPage(final ModalWindow modalWindow, final IModel<ObjectId> realStateIdModel) {
        titleImageUploadForm = new Form<ObjectId>("titleImageUploadForm", realStateIdModel);
        titleImageUploadForm.setMultiPart(true);
        uploadTitleField = new FileUploadField("file1");
        titleImageUploadForm.add(uploadTitleField);
        titleImageUploadForm.add(new WepJsButton("upload", "Upload") {

			private static final long serialVersionUID = 1L;

				@Override
            public void onSubmit() {
                FileUpload uploadFile = uploadTitleField.getFileUpload();
                
                if (uploadFile != null) {
                    saveUploadedFile(uploadFile);
                    
                    ObjectId realStateId = titleImageUploadForm.getModelObject();
                    RealState realState = new DetachableRealStateModel(realStateId.toString()).getObject();                    
                    if (realState != null) {
                  	  realState.setTitleImage(uploadFile.getClientFileName());
                  	  realStateRepository.save(realState);
                    } else {
                  	  realState = new RealState();
                  	  realState.setId(realStateId);
                  	  realState.setTitleImage(uploadFile.getClientFileName());
                  	  realStateRepository.save(realState);
                    }
                }
            }
        });
        add(titleImageUploadForm);
        imageStoreDir = appConfig.getImageStoreDir();
    }

    
    private void saveUploadedFile(FileUpload uploadedFile) {

        if (uploadedFile == null || uploadedFile.getSize() == 0l) {
            return;
        }

        // Create a new file
        String mimeType = uploadedFile.getContentType();
        String clientFileName = uploadedFile.getClientFileName();
        long size = uploadedFile.getSize();

        // id of the apartment for which the images are uploaded
        String realStateId = titleImageUploadForm.getDefaultModelObjectAsString();

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
            createResizedImage(originalImage, newResizedFile, false);

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
