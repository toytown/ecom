package com.ecom.web.components.wizard;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.wizard.IWizardStep;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.ecom.web.components.validation.ErrorLevelsFeedbackMessageFilter;

public class WizardComponent extends Wizard implements Serializable {


	private static final long serialVersionUID = -8874860187906015162L;
	private WizardButtonBar buttonBar;

    /**
     * creates a wizard component out of provided list of steps
     * @param id id of wizard component
     * @param wizardSteps list of {@link WizardStep}s
     * @param showStepNumbers when true, generates the step numbers panel on top of wizard
     */
    public WizardComponent(String id, WizardModel wizardModel, boolean showStepNumbers) {
        super(id);
        int stepsNumber = 0;
        for (Iterator<IWizardStep> it = wizardModel.stepIterator(); it.hasNext();) {
            stepsNumber++;
            WizardStep wizardStep = (WizardStep)it.next();
            wizardStep.setStepNumber(stepsNumber);
        }
        
        add(new WizardStepNumbersPanel("stepNumbersPanel", stepsNumber).setVisible(showStepNumbers));
        
        setDefaultModel(new CompoundPropertyModel<WizardComponent>(this));

        // initialize the wizard with the wizard model we just built
        init(wizardModel);
    
    }
    
    public void disableFeedbackPanelErrorMessages() {
        // validation errors will not be shown in the FeedbackPanel
        FeedbackPanel feedbackPanel = ((FeedbackPanel)get("form:" + Wizard.FEEDBACK_ID));
        if (feedbackPanel != null) {
            feedbackPanel.setFilter(new ErrorLevelsFeedbackMessageFilter(new int[]{FeedbackMessage.ERROR}));
        }
    }
    
    public void enableFeedbackPanelErrorMessages() {
        // validation errors will be shown in the FeedbackPanel
        FeedbackPanel feedbackPanel = ((FeedbackPanel)get("form:" + Wizard.FEEDBACK_ID));
        if (feedbackPanel != null) {
            feedbackPanel.setFilter(null);
        }
    }
    
    public WizardButtonBar getButtonBar() {
        return buttonBar;
    }
    
    /**
     * @inheritDoc
     */
    @Override
    protected Component newButtonBar(String id) {
        buttonBar = new WizardButtonBar(id, this);
        buttonBar.setOutputMarkupId(true);
        return buttonBar;
    }    
    
    /**
     *  Panel rendering the steps of a wizard page 
     */
    private class WizardStepNumbersPanel extends Panel {
        
		private static final long serialVersionUID = -1078320170760794153L;

		public WizardStepNumbersPanel(String id, int stepsNumber) {
            super(id);
            
            add(new Loop("stepList", stepsNumber) {
					private static final long serialVersionUID = 4388288969775260249L;

					@Override
                protected void populateItem(LoopItem item) {
                    final int currIteration = item.getIndex() + 1;
                    item.add(new Label("step", "" + currIteration){
							private static final long serialVersionUID = -335727644970135956L;

								@Override
                        protected void onComponentTag(ComponentTag tag) {
                            super.onComponentTag(tag);
                            int currentStep = ((WizardStep)getActiveStep()).getStepNumber();
                            tag.put("class", "step_" + (currIteration  == currentStep ? "active"  : "grey") + " rounded_all" );
                        }
                    });
                }
                
            });
        }
        
    }

}
