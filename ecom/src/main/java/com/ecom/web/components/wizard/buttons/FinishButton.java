package com.ecom.web.components.wizard.buttons;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.IWizardStep;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class FinishButton extends WizardButton {

    public FinishButton(String id, IWizard wizard) {
        this(id, new Model<String>("Finish"), wizard);
    }
    
    public FinishButton(String id, IModel<String> model, IWizard wizard) {
        super(id, model, wizard);
        setEmphasized(true);
    }
    
    @Override
    public final boolean isEnabled() {
        IWizardStep activeStep = getWizardModel().getActiveStep();
        return (activeStep != null && getWizardModel().isLastStep(activeStep));
    }

    @Override
    protected void onClick() {
        IWizardModel wizardModel = getWizardModel();
        IWizardStep step = wizardModel.getActiveStep();

        // let the step apply any state
        step.applyState();

        // if the step completed after applying the state, notify the wizard
        if (step.isComplete()) {
            getWizardModel().finish();
        } else {
            error(getLocalizer().getString("org.apache.wicket.extensions.wizard.FinishButton.step.did.not.complete", this));
        }
    }
    
    @Override
    protected final void onBeforeRender() {
        getForm().setDefaultButton(this);
        super.onBeforeRender();
    }

}
