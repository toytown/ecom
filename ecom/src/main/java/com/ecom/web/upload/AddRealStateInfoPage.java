package com.ecom.web.upload;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.repository.RealStateRepository;
import com.ecom.web.components.wizard.WizardComponent;
import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.search.HomePage;
import com.ecom.web.utils.SecurePage;

public class AddRealStateInfoPage extends GenericTemplatePage implements SecurePage {

	private static final long serialVersionUID = 2150895889155872074L;
	private static final JavaScriptResourceReference JS_UPLOAD_UTL = new JavaScriptResourceReference(AddRealStateInfoPage.class, "upload_file.js");
	
	@SpringBean
	private RealStateRepository realStateRepository;

	private class UploadRealStateWizard extends WizardComponent implements Serializable {

		private static final long serialVersionUID = -3026931598109913934L;

		public UploadRealStateWizard(String id, WizardModel wizardModel, boolean showStepNumbers) {
			super(id, wizardModel, showStepNumbers);
		}
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new Behavior() {

			private static final long serialVersionUID = 1L;

			@Override
			public void renderHead(Component component, IHeaderResponse response) {
				response.renderJavaScriptReference(JS_UPLOAD_UTL);
			}
		});
	}

	public AddRealStateInfoPage() {
		super();
		WizardModel wizardModel = new WizardModel();

        RealState realState = new RealState();
        realState.setId(new ObjectId());
		final IModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realState);
		wizardModel.add(new SelectOfferStep(new Model<String>("Setup Basic Info ...2"), null, realStateModel));
		wizardModel.add(new BasicInfoStep(new Model<String>("Provide Basic Info ...2"), null, realStateModel));
		wizardModel.add(new ImageUploadStep(new Model<String>("Upload Images...3"), null, realStateModel));
		wizardModel.add(new PreviewStep(new Model<String>("Preview"), null, realStateModel));
		UploadRealStateWizard wizard = new UploadRealStateWizard("addRealStateWizard", wizardModel, true) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onFinish() {
				realStateRepository.save(realStateModel.getObject());
				setResponsePage(HomePage.class);
			}
		};
		wizard.disableFeedbackPanelErrorMessages();
		add(wizard);
	}
	
    public AddRealStateInfoPage(IModel<RealState> realState) {
        super();

        final IModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realState);
        WizardModel wizardModel = new WizardModel();
        wizardModel.add(new SelectOfferStep(new Model<String>("Setup Basic Info ...2"), null, realStateModel));
        wizardModel.add(new BasicInfoStep(new Model<String>("Provide Basic Info ...2"), null, realStateModel));
        wizardModel.add(new ImageUploadStep(new Model<String>("Upload Images...3"), null, realStateModel));
        wizardModel.add(new PreviewStep(new Model<String>("Preview"), null, realStateModel));
        UploadRealStateWizard wizard = new UploadRealStateWizard("addRealStateWizard", wizardModel, true) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onFinish() {
                realStateRepository.save(realStateModel.getObject());
                setResponsePage(HomePage.class);
            }
        };
        wizard.disableFeedbackPanelErrorMessages();
        add(wizard);
    }
    

}
