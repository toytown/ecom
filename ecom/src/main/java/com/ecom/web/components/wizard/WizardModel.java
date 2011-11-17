package com.ecom.web.components.wizard;

import org.apache.wicket.extensions.wizard.IWizardStep;

/**
 * Extension of {@link org.apache.wicket.extensions.wizard.WizardModel} 
 */
public class WizardModel extends org.apache.wicket.extensions.wizard.WizardModel {

    public WizardModel() {
        super();
    }
    /**
     * Checks if the save button should be enabled. By default it checks, if all steps are complete by calling {@link IWizardStep#isComplete}.
     * Overwrite it of you need another behavior. 
     * 
     * @return <tt>true</tt> if the Save button should be enabled, <tt>false</tt> otherwise.
     */
    public boolean isSaveAvailable() {
        return super.allStepsComplete();
    }
}
