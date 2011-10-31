package com.ecom.web.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.MultiFileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.lang.Bytes;

import com.ecom.domain.RealState;


public class FileUploadForm extends Form<RealState> {

	private static final long serialVersionUID = -8486596461194196986L;
	
	// collection that will hold uploaded FileUpload objects
	private final Collection<FileUpload> uploads = new ArrayList<FileUpload>();
	private static final Logger logger = Logger.getLogger(FileUploadForm.class);


	public FileUploadForm(String name, IModel<RealState> advertModel) {
		super(name, advertModel);

		// set this form to multi-part mode (always needed for uploads!)
		setMultiPart(true);

		// Add one multi-file upload field
		add(new MultiFileUploadField("fileInput", new PropertyModel<Collection<FileUpload>>(this, "uploads"), 5));

		// Set maximum size to 1000K for demo purposes
		setMaxSize(Bytes.kilobytes(1000));

	}

	/**
	 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
	 */
	@Override
	protected void onSubmit() {
		Iterator<FileUpload> it = uploads.iterator();
		
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

//	private void writeImageToFile(Advertisement appartment, FileUpload upload) {
//		String imageStore = appConfig.getImageStore();
//		File newFile = new File(imageStore + File.separatorChar + appartment.getImageDir(), upload.getClientFileName());
//		// Check new file, delete if it already existed
//		checkFileExists(newFile);
//		try {
//			// Save to new file
//			newFile.createNewFile();
//			upload.writeTo(newFile);
//			
//			logger.info("saved file under location : " + newFile.getAbsolutePath());
//		} catch (Exception e) {
//			throw new IllegalStateException("Unable to write file");
//		}		
//	}
	
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
}
