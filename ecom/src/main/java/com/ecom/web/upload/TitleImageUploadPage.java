package com.ecom.web.upload;

import java.io.IOException;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.service.impl.RealStateImageService;
import com.ecom.web.components.buttons.IndicatingAjaxSubmitLink;
import com.ecom.web.main.GenericTemplatePage;

public class TitleImageUploadPage extends GenericTemplatePage {

    private static final long serialVersionUID = 1942366724719884862L;

    private Form<Void> titleImageUploadForm = null;

    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
    
    @SpringBean(name = "imageService")
    private RealStateImageService imageService;

    private final FileUploadField uploadTitleField;

    @Override
    public void onInitialize() {
        super.onInitialize();
        Injector.get().inject(this);
        feedbackPanel.setOutputMarkupId(true);
    }
    
    public TitleImageUploadPage(final ModalWindow modalWindow, final IModel<RealState> realStateModel) {
        titleImageUploadForm = new Form<Void>("titleImageUploadForm");
        titleImageUploadForm.setMultiPart(true);
        uploadTitleField = new FileUploadField("file1");
        titleImageUploadForm.add(uploadTitleField);

        
        IndicatingAjaxSubmitLink uploadLink = new IndicatingAjaxSubmitLink("upload", Model.of("Upload")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                FileUpload uploadFile = uploadTitleField.getFileUpload();
                RealState realState = realStateModel.getObject();
                saveUploadedFiles(uploadFile, realState);
                realStateModel.setObject(realState);
                StringResourceModel model = new StringResourceModel("msg_successful_upload", TitleImageUploadPage.this, null, new Object[] { uploadFile.getClientFileName() });
                info(model.getObject());
                target.add(feedbackPanel);
            }
        };
        
        
        titleImageUploadForm.add(uploadLink);      
        add(feedbackPanel);
        add(titleImageUploadForm);


    }

    private void saveUploadedFiles(FileUpload uploadedFile, RealState realState) {

        if (uploadedFile != null && uploadedFile.getClientFileName() != null) {
            try {
                if (realState != null && ObjectId.isValid(realState.getTitleImageId())) {
                    imageService.deleteImage(realState, realState.getTitleImageId());
                    realState.removeTitleImages();
                    //saves the newly uploaded title image
                }
                imageService.saveUploadedImageFileInDB(uploadedFile.getClientFileName(), uploadedFile.getInputStream(), realState, true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
