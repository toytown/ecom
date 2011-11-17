package com.ecom.web.components.wizard.buttons;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.IWizardStep;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.components.wizard.WizardStep;

/**
 * Save button of wizard enables to save even incomplete state of a wizard without a need to come up to the last step.
 * Unlike {@link FinishButton} it therefore is available and visible regardles of what the {@link WizardStep#isComplete()} returns;
 */
public class SaveButton extends WizardButton {


    public SaveButton(String id, IWizard wizard) {
        this(id, new Model<String>("Save"), wizard);
    }
    
    public SaveButton(String id, IModel<String> model, IWizard wizard) {
        super(id, model, wizard);
    }
    
    @Override
    public final boolean isEnabled() {
        //IWizardStep activeStep = getWizardModel().getActiveStep();
        return ((WizardModel)getWizardModel()).isSaveAvailable();
    }

    @Override
    protected void onClick() {
        IWizardModel wizardModel = getWizardModel();
        IWizardStep step = wizardModel.getActiveStep();

        // let the step apply any state
        step.applyState();


    }

}
