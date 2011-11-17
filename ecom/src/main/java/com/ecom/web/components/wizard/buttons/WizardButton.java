package com.ecom.web.components.wizard.buttons;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import com.ecom.web.components.buttons.WepJsButton;



/**
 * Base class for buttons that work with {@link IWizard the wizard component}.
 * <p>
 * When wizard buttons are presses (and they pass validation if that is
 * relevant), they pass control to {@link #onClick() their action method}, which
 * should do the real work.
 * </p>
 */
public abstract class WizardButton extends WepJsButton {

    private static final long serialVersionUID = 1L;
    
    public static final String BUTTON_BACK = "back";
    public static final String BUTTON_CANCEL = "cancel";
    public static final String BUTTON_CONTINUE = "continue";
    public static final String BUTTON_SAVE = "save";
    public static final String BUTTON_FINISH = "finish";
    
    /**
     * The enclosing wizard.
     */
    private final IWizard wizard;
    
    public WizardButton(String id, String label, IWizard wizard) {
        super(id, label);
        this.wizard = wizard;
    }
    
    public WizardButton(String id, IModel<String> model, Form<?> form, IWizard wizard) {
        super(id, model, form);
        this.wizard = wizard;
    }

    public WizardButton(String id, IModel<String> model, IWizard wizard) {
        super(id, model);
        this.wizard = wizard;
    }

    public WizardButton(String id, String label, Form<?> form, IWizard wizard) {
        super(id, label, form);
        this.wizard = wizard;
    }

    /**
     * Gets the {@link IWizard}.
     * 
     * @return The wizard
     */
    protected final IWizard getWizard() {
        return wizard;
    }

    /**
     * Gets the {@link IWizardModel wizard model}.
     * 
     * @return The wizard model
     */
    protected final IWizardModel getWizardModel() {
        return getWizard().getWizardModel();
    }

    /**
     * Called when this button is clicked.
     */
    protected abstract void onClick();

    /**
     * @see org.apache.wicket.markup.html.form.Button#onSubmit()
     */
    @Override
    public final void onSubmit() {
        onClick();
    }


}
