package com.ecom.web.components.buttons;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.AppendingStringBuffer;

/**
 * Extension of {@link SubmitLink}, that renders as a button defined in WEP
 * styleguide and additionaly allows to define JavaScript invoked onClick by
 * exposing {@link #getOnClickJavaScript} method to override.
 */
public class WepJsButton extends SubmitLink {

	private static final long serialVersionUID = 1L;
	private boolean emphasized;
	private static final String CSS_EMPHASIZED = "emphasized_button";
	private static final String CSS_STANDARD = "standard_button";

	public WepJsButton(String id, String label) {
		this(id, new Model<String>(label));
	}

	public WepJsButton(String id, IModel<String> model) {
		super(id, model);
	}

	public WepJsButton(String id, String label, Form<?> form) {
		this(id, new Model<String>(label), form);
	}

	public WepJsButton(String id, IModel<String> model, Form<?> form) {
		super(id, model, form);
	}

	/**
	 * @inheritDoc
	 * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);

		if (!isLinkEnabled()) {
			disableLink(tag);
		} else {
			tag.setName("a");
			String value = getDefaultModelObjectAsString();
			tag.put("title", value);
			tag.put("href", "#");
			tag.put("class", (emphasized ? CSS_EMPHASIZED : CSS_STANDARD));
			tag.put("onclick", getOnClickJavaScript());

		}
	}

	@Override
	public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		final AppendingStringBuffer buffer = new AppendingStringBuffer("<span>" + getDefaultModelObjectAsString() + "</span>");
		replaceComponentTagBody(markupStream, openTag, buffer);
	}

	/**
	 * Returns the javascript to be triggered onClick of the button. This is
	 * typically the method to be overriden by subclasses. In a default
	 * implementation it just behaves as {@link SubmitLink}
	 * 
	 * @return
	 */
	protected String getOnClickJavaScript() {
		return getTriggerJavaScript();
	}

	@Override
	protected void disableLink(final ComponentTag tag) {
		tag.remove("onclick");
		tag.put("class", (emphasized ? CSS_EMPHASIZED : CSS_STANDARD) + " disabled_button");
	}

	/**
	 * set this button as emphasized, i.e. it will be a bit darker, than
	 * non-emhasized ones
	 * 
	 * @param emphasized
	 * @return this
	 */
	public WepJsButton setEmphasized(boolean emphasized) {
		this.emphasized = emphasized;
		return this;
	}

}
