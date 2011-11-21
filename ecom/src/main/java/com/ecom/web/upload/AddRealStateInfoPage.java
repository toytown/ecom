package com.ecom.web.upload;

import org.apache.wicket.model.Model;

import com.ecom.web.components.wizard.WizardComponent;
import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.main.GenericTemplatePage;

public class AddRealStateInfoPage extends GenericTemplatePage {

	private static final long serialVersionUID = 2150895889155872074L;

	private static class UploadRealStateWizard extends WizardComponent {

        public UploadRealStateWizard(String id, WizardModel wizardModel, boolean showStepNumbers) {
            super(id, wizardModel, showStepNumbers);
        }
	    
	}
	
	public AddRealStateInfoPage() {
		super();

		WizardModel wizardModel = new WizardModel();
		wizardModel.add(new BasicInfoStep(new Model("Step ...1"), null));
		wizardModel.add(new ImageUploadStep(new Model("Upload Images"), null));		
		UploadRealStateWizard wizard = new UploadRealStateWizard("addRealStateWizard", wizardModel, true);
		
	   
		add(wizard);
	   
		
	}
	
}
