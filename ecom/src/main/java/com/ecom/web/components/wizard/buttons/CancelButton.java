package com.ecom.web.components.wizard.buttons;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CancelButton extends WizardButton {

    private static final long serialVersionUID = 1L;
    
    public CancelButton(String id, IWizard wizard) {
        this(id, new Model<String>("Cancel"), wizard);
    }
    
    public CancelButton(String id, IModel<String> model, IWizard wizard) {
        super(id, model, wizard);
        setDefaultFormProcessing(false);
    }
    
    @Override
    public final boolean isEnabled() {
        return true;
    }

    @Override
    public final boolean isVisible() {
        return getWizardModel().isCancelVisible();
    }

    @Override
    public final void onClick() {
        getWizardModel().cancel();
    }

}
