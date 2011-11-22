package com.ecom.web.upload;

import org.apache.wicket.model.Model;
import org.bson.types.ObjectId;

import com.ecom.web.components.wizard.WizardComponent;
import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.main.GenericTemplatePage;

public class AddRealStateInfoPage extends GenericTemplatePage {

	private static final long serialVersionUID = 2150895889155872074L;

	private static class UploadRealStateWizard extends WizardComponent {

		private static final long serialVersionUID = -3026931598109913934L;

		public UploadRealStateWizard(String id, WizardModel wizardModel, boolean showStepNumbers) {
            super(id, wizardModel, showStepNumbers);
        }
	    
	}
	
	public AddRealStateInfoPage() {
		super();

		WizardModel wizardModel = new WizardModel();
		
		ObjectId realStateId = new ObjectId();
		
		wizardModel.add(new BasicInfoStep(new Model<String>("Step ...1"), null, realStateId));
		wizardModel.add(new ImageUploadStep(new Model<String>("Upload Images"), null, realStateId));		
		UploadRealStateWizard wizard = new UploadRealStateWizard("addRealStateWizard", wizardModel, true);
		
	   
		add(wizard);
	   
		
	}
	
}
