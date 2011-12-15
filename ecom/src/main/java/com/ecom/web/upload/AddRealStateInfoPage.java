package com.ecom.web.upload;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.repository.RealStateRepository;
import com.ecom.web.components.wizard.WizardComponent;
import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.search.HomePage;

public class AddRealStateInfoPage extends GenericTemplatePage {

    private static final long serialVersionUID = 2150895889155872074L;
    
    @SpringBean
    private RealStateRepository realStateRepository;
    
    private class UploadRealStateWizard extends WizardComponent {

        private static final long serialVersionUID = -3026931598109913934L;

        public UploadRealStateWizard(String id, WizardModel wizardModel, boolean showStepNumbers) {
            super(id, wizardModel, showStepNumbers);
        }
    }

    public AddRealStateInfoPage() {
        super();
        WizardModel wizardModel = new WizardModel();

       // IModel<ObjectId> realStateObjId = new Model<ObjectId>(new ObjectId());
        //wizardModel.add(new SelectOfferStep(new Model<String>("Chose real state type 1"), null, realStateObjId));
        RealState realState = new RealState();
        realState.setId(new ObjectId());
        
        final IModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realState);
        wizardModel.add(new SelectOfferStep(new Model<String>("Setup Basic Info ...2"), null, realStateModel));
        wizardModel.add(new BasicInfoStep(new Model<String>("Provide Basic Info ...2"), null, realStateModel));
        wizardModel.add(new ImageUploadStep(new Model<String>("Upload Images...3"), null, realStateModel));
        wizardModel.add(new PreviewStep(new Model<String>("Preview"), null, realStateModel));
        UploadRealStateWizard wizard = new UploadRealStateWizard("addRealStateWizard", wizardModel, true) {
            
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
