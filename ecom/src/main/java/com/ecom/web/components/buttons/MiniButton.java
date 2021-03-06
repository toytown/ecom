package com.ecom.web.components.buttons;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

/**
 * Extension of {@link Link}, that renders as a button defined in WEP styleguide
 * 
 */
public abstract class MiniButton<T> extends StatelessLink<T> {

	private static final long serialVersionUID = 1976291861141359223L;
	private boolean emphasized;
	private static final String CSS_EMPHASIZED = "emphasized_button";
	private static final String CSS_STANDARD = "standard_button";
	private static final String CSS_SMALL = "small_button";
	
	private boolean isSmallButton = true;


	public MiniButton(String id, IModel<String> model) {
		super(id);
		this.setDefaultModel(model);
	}

	public MiniButton(String id, IModel<String> model, boolean smallButton) {
		super(id);
		this.setDefaultModel(model);
		this.isSmallButton = smallButton;
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
			if (this.isSmallButton) {
				tag.put("class", CSS_SMALL);
			} else {
				tag.put("class", (emphasized ? CSS_EMPHASIZED : CSS_STANDARD));
			}
		}
	}

	@Override
	public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		final AppendingStringBuffer buffer = new AppendingStringBuffer("<span>" + this.getDefaultModelObjectAsString() + "</span>");
		replaceComponentTagBody(markupStream, openTag, buffer);
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
	public MiniButton<T> setEmphasized(boolean emphasized) {
		this.emphasized = emphasized;
		return this;
	}

	public boolean isSmallButton() {
		return isSmallButton;
	}

	public void setSmallButton(boolean isSmallButton) {
		this.isSmallButton = isSmallButton;
	}
	

}
