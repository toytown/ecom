package com.ecom.web.components.wizard.buttons;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.IWizardStep;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ContinueButton extends WizardButton {

    private static final long serialVersionUID = 1L;
    
    public ContinueButton(String id, IWizard wizard) {
        this(id, new Model<String>("Continue"), wizard);
    }
    
    public ContinueButton(String id, IModel<String> model, IWizard wizard) {
        super(id, model, wizard);
        setEmphasized(true);
    }

    /**
     * @see org.apache.wicket.Component#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return getWizardModel().isNextAvailable();
    }
    
    @Override
    protected void onClick() {
        IWizardModel wizardModel = getWizardModel();
        IWizardStep step = wizardModel.getActiveStep();

        // let the step apply any state
        step.applyState();

        // if the step completed after applying the state, move the
        // model onward
        if (step.isComplete()) {
            wizardModel.next();
        } else {
            error(getLocalizer().getString("org.apache.wicket.extensions.wizard.NextButton.step.did.not.complete", this));
        }
    }
    
    /**
     * @see org.apache.wicket.Component#onBeforeRender()
     */
    @Override
    protected final void onBeforeRender() {
        getForm().setDefaultButton(this);
        super.onBeforeRender();
    }
}
