package com.ecom.web.upload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.RealStateImage;
import com.ecom.service.impl.RealStateImageService;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.components.wizard.WizardStep;
import com.ecom.web.data.RealStateImageDataProvider;

public class ImageUploadStep extends WizardStep {

    private static final long serialVersionUID = -7463392511943675380L;
    public final Collection<FileUpload> uploads = new ArrayList<FileUpload>();

    @SpringBean
    private RealStateImageService imageService;

    private FileUploadField file1;
    private FileUploadField file2;
    private FileUploadField file3;
    private FileUploadField file4;
    private FileUploadField file5;
    private FileUploadField file6;
    private FileUploadField file7;
    private FileUploadField file8;
    private FileUploadField file9;
    private FileUploadField file10;

    private FileUploadForm<ObjectId> imageUploadForm;
    private WebMarkupContainer imageContainer = new WebMarkupContainer("uploadedImagesContainer");

    public ImageUploadStep(IModel<String> title, IModel<String> summary, final IModel<ObjectId> realStateIdModel) {
        super(title, summary);
        Injector.get().inject(this);

        imageUploadForm = new FileUploadForm<ObjectId>("uploadForm", realStateIdModel);
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

        imageUploadForm.add(new Button("upload") {

            private static final long serialVersionUID = 2718354305648397798L;

            @Override
            public void onSubmit() {
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

                saveUploadedFiles(uploadedFilesList, imageUploadForm.getModelObject());

                String realStateId = imageUploadForm.getDefaultModelObjectAsString();
                final ISortableDataProvider<RealStateImage> dataProvider = new RealStateImageDataProvider(realStateId);
                
                DataView<RealStateImage> imgListView = new DataView<RealStateImage>("imagesListView", dataProvider) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void populateItem(Item<RealStateImage> item) {
                        RealStateImage img = item.getModel().getObject();

                        if (!img.isTitleImage()) {
                            item.add(new StaticImage("img", new Model<String>(img.getImageURL())));
                            item.add(new Link<String>("deleteLink", new Model<String>(img.getId().toString())) {
                                
                                private static final long serialVersionUID = 1L;
                                
                                @Override
                                public void onClick() {
                                    String imgId = this.getDefaultModelObjectAsString();
                                    imageService.deleteImage(new ObjectId(imgId), false);
                                    this.setVisible(false);
                                    imageContainer.addOrReplace(this);
                                }
                            });
                        }
                    }
                };

                imageContainer.addOrReplace(imgListView);
                imageContainer.setVisible(true);

            }

        });

        add(imageUploadForm);
        imageContainer.setOutputMarkupId(true);
        imageContainer.setVisible(false);
        add(imageContainer);
    }

    private void saveUploadedFiles(List<FileUpload> uploadedFiles, ObjectId realStateId) {
        for (FileUpload uploadedFile : uploadedFiles) {
            imageService.saveUploadedImageFile(uploadedFile, realStateId, false);
        }
    }
}
