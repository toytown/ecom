package com.ecom.web.upload;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateImageRepository;
import com.ecom.web.components.wizard.WizardStep;

public class ImageUploadStep extends WizardStep {

	private static final long serialVersionUID = -7463392511943675380L;
	public final Collection<FileUpload> uploads = new ArrayList<FileUpload>();

	@SpringBean
	private RealStateImageRepository imageRepository;

	@SpringBean
	private AppConfig appConfig;

	FileUploadField file1;
	FileUploadField file2 = null;
	FileUploadField file3 = null;
	FileUploadField file4 = null;
	FileUploadField file5 = null;
	FileUploadField file6 = null;
	FileUploadField file7 = null;
	FileUploadField file8 = null;
	FileUploadField file9 = null;
	FileUploadField file10 = null;

	private File imageStoreDir;
	private FileUploadForm imageUploadForm;
	
	private List<String> filesSelected = new ArrayList<String>();
	
	public ImageUploadStep(IModel<String> title, IModel<String> summary) {
		super(title, summary);
		Injector.get().inject(this);
		imageStoreDir = appConfig.getImageStoreDir();

		final String realStateId = UUID.randomUUID().toString();
		IModel<String> realStateIdModel = new Model<String>(realStateId);


		
		imageUploadForm = new FileUploadForm("uploadForm", realStateIdModel) {

			private static final long serialVersionUID = -172351541720463732L;

			/**
			 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
			 */
			@Override
			protected void onSubmit() {
				List<FileUpload> uploadedFilesList = new ArrayList<FileUpload>();
				FileUpload uploadedFile1 = file1.getFileUpload();
				FileUpload uploadedFile2 = file2.getFileUpload();
				FileUpload uploadedFile3 = file3.getFileUpload();
				FileUpload uploadedFile4 = file4.getFileUpload();
				FileUpload uploadedFile5 = file5.getFileUpload();
				FileUpload uploadedFile6 = file6.getFileUpload();
				FileUpload uploadedFile7 = file7.getFileUpload();
				FileUpload uploadedFile8 = file8.getFileUpload();
				FileUpload uploadedFile9 = file9.getFileUpload();
				FileUpload uploadedFile10 = file10.getFileUpload();

				uploadedFilesList.add(uploadedFile1);
				uploadedFilesList.add(uploadedFile2);
				uploadedFilesList.add(uploadedFile3);
				uploadedFilesList.add(uploadedFile4);
				uploadedFilesList.add(uploadedFile5);
				uploadedFilesList.add(uploadedFile6);
				uploadedFilesList.add(uploadedFile7);
				uploadedFilesList.add(uploadedFile8);
				uploadedFilesList.add(uploadedFile9);
				uploadedFilesList.add(uploadedFile10);
				
				saveUploadedFile(uploadedFilesList);
			}

		};
		imageUploadForm.setMultiPart(true);

		IModel<List<FileUpload>> model = new PropertyModel<List<FileUpload>>(this, "uploads");
		imageUploadForm.add(file1 = new FileUploadField("file1", model));
		imageUploadForm.add(file2 = new FileUploadField("file2", model));
		imageUploadForm.add(file3 = new FileUploadField("file3", model));
		imageUploadForm.add(file4 = new FileUploadField("file4", model));
		imageUploadForm.add(file5 = new FileUploadField("file5", model));
		imageUploadForm.add(file6 = new FileUploadField("file6", model));
		imageUploadForm.add(file7 = new FileUploadField("file7", model));
		imageUploadForm.add(file8 = new FileUploadField("file8", model));
		imageUploadForm.add(file9 = new FileUploadField("file9", model));
		imageUploadForm.add(file10 = new FileUploadField("file10", model));

		file1.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				file1.updateModel();
				System.out.println(file1.getDefaultModelObjectAsString());
				filesSelected.add(file1.getValue());

			}

		});
		
		imageUploadForm.add(new Button("upload"));

		add(imageUploadForm);
	}

	private void saveUploadedFile(List<FileUpload> uploadedFiles) {

		for (FileUpload upload : uploadedFiles) {

			if (upload == null || upload.getSize() == 0l) {
				continue;
			}
			// Create a new file
			String mimeType = upload.getContentType();
			String clientFileName = upload.getClientFileName();
			long size = upload.getSize();
			System.out.println("File name ...." + clientFileName);

			// id of the apartment for which the images are uploaded
			String realStateId = imageUploadForm.getDefaultModelObjectAsString();

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
