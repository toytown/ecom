package com.ecom.web.upload;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.bson.types.ObjectId;

import com.ecom.web.components.wizard.WizardComponent;
import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.search.HomePage;

public class AddRealStateInfoPage extends GenericTemplatePage {

    private static final long serialVersionUID = 2150895889155872074L;

    private class UploadRealStateWizard extends WizardComponent {

        private static final long serialVersionUID = -3026931598109913934L;

        public UploadRealStateWizard(String id, WizardModel wizardModel, boolean showStepNumbers) {
            super(id, wizardModel, showStepNumbers);
        }

        @Override
        public void onFinish() {
            setResponsePage(HomePage.class);
        }

        @Override
        public void onCancel() {

        }
    }

    public AddRealStateInfoPage() {
        super();
        WizardModel wizardModel = new WizardModel();

        IModel<ObjectId> realStateObjId = new Model<ObjectId>(new ObjectId());
        wizardModel.add(new BasicInfoStep(new Model<String>("Step ...1"), null, realStateObjId));
        wizardModel.add(new ImageUploadStep(new Model<String>("Upload Images"), null, realStateObjId));
        UploadRealStateWizard wizard = new UploadRealStateWizard("addRealStateWizard", wizardModel, true);

        add(wizard);
    }

}
