package com.ecom.web.components.buttons;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

import com.ecom.web.components.validation.CustomBusyIndicator;

public class IndicatingAjaxSubmitLink extends AjaxSubmitLink implements IAjaxIndicatorAware {
	private static final long serialVersionUID = -4920432859082882826L;
	private static final String CSS_EMPHASIZED = "emphasized_button";
	private static final String CSS_STANDARD = "standard_button";
	private final CustomBusyIndicator indicatorAppender = new CustomBusyIndicator();

	private boolean emphasized;
	
	public IndicatingAjaxSubmitLink(String id, IModel<String> model) {
		super(id);
		this.setDefaultModel(model);
		this.add(indicatorAppender);
	}


    @Override
    public String getAjaxIndicatorMarkupId() {
        return indicatorAppender.getMarkupId();
    }


    @Override
    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        // TODO Auto-generated method stub
        
    }


    @Override
    protected void onError(AjaxRequestTarget target, Form<?> form) {
        // TODO Auto-generated method stub
        
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
        }
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        final AppendingStringBuffer buffer = new AppendingStringBuffer("<span>" + getDefaultModelObjectAsString() + "</span>");
        replaceComponentTagBody(markupStream, openTag, buffer);
    }


    @Override
    protected void disableLink(final ComponentTag tag) {
        tag.put("class", (emphasized ? CSS_EMPHASIZED : CSS_STANDARD) + " disabled_button");
    }

    /**
     * set this button as emphasized, i.e. it will be a bit darker, than
     * non-emhasized ones
     * 
     * @param emphasized
     * @return this
     */
    public IndicatingAjaxSubmitLink setEmphasized(boolean emphasized) {
        this.emphasized = emphasized;
        return this;
    }
}
