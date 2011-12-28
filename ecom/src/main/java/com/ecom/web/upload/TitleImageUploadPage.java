package com.ecom.web.upload;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.RealState;
import com.ecom.service.impl.RealStateImageService;
import com.ecom.web.components.buttons.IndicatingAjaxSubmitLink;
import com.ecom.web.main.GenericTemplatePage;

public class TitleImageUploadPage extends GenericTemplatePage {

    private static final long serialVersionUID = 1942366724719884862L;

    private Form<Void> titleImageUploadForm = null;

    @SpringBean(name = "imageService")
    private RealStateImageService imageService;

    private final FileUploadField uploadTitleField;

    public TitleImageUploadPage(final ModalWindow modalWindow, final IModel<RealState> realStateModel) {
        titleImageUploadForm = new Form<Void>("titleImageUploadForm");
        titleImageUploadForm.setMultiPart(true);
        uploadTitleField = new FileUploadField("file1");
        titleImageUploadForm.add(uploadTitleField);
        IndicatingAjaxSubmitLink uploadLink = new IndicatingAjaxSubmitLink("upload", new Model("Upload")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                FileUpload uploadFile = uploadTitleField.getFileUpload();
                RealState realState = realStateModel.getObject();
                saveUploadedFiles(uploadFile, realState);
                realStateModel.setObject(realState);
               //TODO modalWindow.close(target);
            }
        };
        
        titleImageUploadForm.add(uploadLink);        
        add(titleImageUploadForm);

    }

    private void saveUploadedFiles(FileUpload uploadedFile, RealState realState) {

        if (uploadedFile != null && uploadedFile.getClientFileName() != null) {
            try {
                if (!StringUtils.isEmpty(realState.getTitleImageId())) {
                    imageService.deleteImage(realState, realState.getTitleImageId());
                    realState.removeTitleImages();
                }
                //saves the newly uploaded title image
                imageService.saveUploadedImageFileInDB(uploadedFile.getClientFileName(), uploadedFile.getInputStream(), realState, true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
