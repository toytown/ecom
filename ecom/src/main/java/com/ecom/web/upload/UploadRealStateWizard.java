package com.ecom.web.upload;

import org.apache.wicket.model.IModel;

import com.ecom.web.components.wizard.WizardComponent;
import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.components.wizard.WizardStep;

public class UploadRealStateWizard extends WizardComponent {

	private static final long serialVersionUID = 4564355287570612619L;

	public UploadRealStateWizard(String id, WizardModel wizardModel, boolean showStepNumbers) {
		super(id, wizardModel, showStepNumbers);
	}

	protected class ImageUploadStep extends WizardStep {

		private static final long serialVersionUID = 4891384497087272754L;

		public ImageUploadStep(IModel<String> title, IModel<String> summary) {
			super(title, summary);

		}
	}
}
