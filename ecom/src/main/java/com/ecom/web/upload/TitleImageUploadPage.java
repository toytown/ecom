package com.ecom.web.upload;

import java.io.IOException;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.service.impl.RealStateImageService;
import com.ecom.web.components.buttons.WepJsButton;
import com.ecom.web.main.GenericTemplatePage;

public class TitleImageUploadPage extends GenericTemplatePage {

    private static final long serialVersionUID = 1942366724719884862L;

    private Form<ObjectId> titleImageUploadForm = null;

    @SpringBean(name = "imageService")
    private RealStateImageService imageService;

    private final FileUploadField uploadTitleField;

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
                ObjectId realStateId = titleImageUploadForm.getModelObject();
                saveUploadedFiles(uploadFile, realStateId);
            }
        });
        add(titleImageUploadForm);
    }

    private void saveUploadedFiles(FileUpload uploadedFile, ObjectId realStateId) {

        if (uploadedFile != null && uploadedFile.getClientFileName() != null) {
            try {
                imageService.saveUploadedImageFileInDB(uploadedFile.getClientFileName(), uploadedFile.getInputStream(), realStateId, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
