package com.ecom.web.components.wizard.buttons;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class BackButton extends WizardButton {

    private static final long serialVersionUID = 1L;
        
    public BackButton(String id, IWizard wizard) {
        this(id, new Model<String>("Back"), wizard);
    }
    
    public BackButton(String id, IModel<String> model, IWizard wizard) {
        super(id, model, wizard);
        setDefaultFormProcessing(false);
    }
    
    @Override
    public boolean isEnabled() {
        return getWizardModel().isPreviousAvailable();
    }

    @Override
    public void onClick() {
        getWizardModel().previous();
    }

}
