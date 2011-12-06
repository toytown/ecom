package com.ecom.web.components.wizard;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class WizardStep extends org.apache.wicket.extensions.wizard.WizardStep {

	private static final long serialVersionUID = 1L;
	private Integer stepNumber;
	private WizardButtonBar parentButtonBar;

	public WizardStep(String title, String summary) {
		this(new Model<String>(title), new Model<String>(summary));
	}

	public WizardStep(String title, String summary, IModel<?> model) {
		this(new Model<String>(title), new Model<String>(summary), model);
	}

	public WizardStep(IModel<String> title, IModel<String> summary) {
		super(title, summary);
	}

	public WizardStep(IModel<String> title, IModel<String> summary, IModel<?> model) {
		super(title, summary, model);
	}

	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	/**
	 * @see org.apache.wicket.extensions.wizard.IWizardStep#getHeader(java.lang.String,
	 *      org.apache.wicket.Component,
	 *      org.apache.wicket.extensions.wizard.IWizard)
	 */
	@Override
	public Component getHeader(String id, Component parent, IWizard wizard) {
		return new Header(id, wizard);
	}

	public void setParentButtonBar(WizardButtonBar parentButtonBar) {
		this.parentButtonBar = parentButtonBar;
	}

	public WizardButtonBar getParentButtonBar() {
		return parentButtonBar;
	}

	/**
	 * Default header for wizards.
	 */
	private class Header extends Panel {
		private static final long serialVersionUID = 1L;

		/**
		 * Construct.
		 * 
		 * @param id
		 *           The component id
		 * @param wizard
		 *           The containing wizard
		 */
		public Header(final String id, final IWizard wizard) {
			super(id);
			setDefaultModel(new CompoundPropertyModel<IWizard>(wizard));
			add(new Label("title", new AbstractReadOnlyModel<String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return getTitle();
				}
			}).setEscapeModelStrings(false));
			add(new Label("summary", new AbstractReadOnlyModel<String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return getSummary();
				}
			}).setEscapeModelStrings(false));
		}
	}
}
