package com.ecom.web.upload;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.file.Folder;

public class UploadPanel extends Panel {

	private static final long serialVersionUID = 4398431605730737712L;

	public UploadPanel(String id, final Folder uploadFolder) {
		super(id);

		// Create feedback panels
		final FeedbackPanel uploadFeedback = new FeedbackPanel("uploadFeedback");

		// Add uploadFeedback to the page itself
		add(uploadFeedback);

		// Add folder view
		add(new Label("dir", uploadFolder.getAbsolutePath()));
		
		LoadableDetachableModel<List<File>> fileModel = new LoadableDetachableModel<List<File>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<File> load() {
				return Arrays.asList(uploadFolder.listFiles());
			}
		};
		
		ListView<File> fileListView = new ListView<File>("fileList", fileModel){

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<File> listItem) {
				final File file = listItem.getModelObject();
				listItem.add(new Label("file", file.getName()));
				listItem.add(new Link<Void>("delete") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
//						final Advertisement appartment = apptAdvertModel.getObject();
//						removeFile(appartment, file);
					}
				});
			}		
		};
		
		// Add simple upload form, which is hooked up to its feedback panel by
		// virtue of that panel being nested in the form.
		final FileUploadForm simpleUploadForm = new FileUploadForm("simpleUpload", null);
		
		add(fileListView);

		add(simpleUploadForm);

		final Panel previewPanel = new EmptyPanel("previewPanel");
		
		simpleUploadForm.add(new SubmitLink("save") {

			private static final long serialVersionUID = 1L;
//			Advertisement appartmentAdvert = simpleUploadForm.getModelObject();
//			@Override
//			public void onSubmit() {
//				simpleUploadForm.setVisible(false);
//				previewPanel.replaceWith(new RealStatePreviewPanel("previewPanel", appartmentAdvert));
//			}
			
		});
		add(previewPanel);
	}

	
	
//	private void removeFile(Advertisement advert, File fileToRemove) {
//	
//		//new entry
////		if (advert.getId() == null) {
////			Files.remove(fileToRemove);
////		} else {
////			Advertisement advertNew = advertisementSearchService.findById(advert.getId());
////			List<Images> images = advertNew.getImages();	
////			Images imageDel = null;
////		
////			for (Images image : images) {
////				
////				if (image.getId() != null && image.getImageName().equals(fileToRemove.getName())) {
////					imageDel = image;
////					break;
////				} else {
////					Files.remove(fileToRemove);
////					info("Deleted " + fileToRemove);
////				}
////			}
////			
////			if (imageDel != null) {
////				images.remove(imageDel);
////				advertisementSearchService.update(advertNew);
////				advertisementSearchService.deleteImage(imageDel);
////				Files.remove(fileToRemove);
////				info("Deleted " + fileToRemove);				
////			} 
////		}
//	}
	
}
