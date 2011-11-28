package com.ecom.web.components.validation;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.AppendingStringBuffer;

/**
 * Label displaying validation error messages as a bubble error for FormComponents.
 * <p>
 * You can use this Label to show the error message near the actual
 * FormComponent, instead of in the FeedbackPanel It's safe to remove the
 * FeedbackPanel if you use this class for every FormComponent in your Form.
 * <p>
 */
public class ValidationErrorLabel<T> extends Label {

    /**
     * Field component holds a reference to the {@link Component} this
     * ValidationErrorLabel belongs to
     */
    private FormComponent<T> component;
    /** Field text holds a model of the text to be shown in the ValidationErrorLabel */
    private IModel<?> text = null;

    /**
     * Call this constructor if you just want to display the FeedbackMessage of
     * the component
     * 
     * @param id
     *            The non-null id of this component
     * @param component
     *            The {@link FormComponent} to show the FeedbackMessage for.
     */
    public ValidationErrorLabel(String id, FormComponent<T> component) {
        super(id);
        this.component = component;
    }

    /**
     * Call this constructor if you want to display a custom text
     * 
     * @param id
     *            The non-null id of this component
     * @param component
     *            The {@link FormComponent} to show the custom text for.
     * @param text
     *            The custom text to show when the FormComponent has a
     *            FeedbackMessage
     */
    public ValidationErrorLabel(String id, FormComponent<T> component, String text) {
        this(id, component, new Model<String>(text));
    }

    /**
     * Call this constructor if you want to display a custom model (for easy
     * i18n)
     * 
     * @param id
     *            The non-null id of this component
     * @param component
     *            The {@link FormComponent} to show the custom model for.
     * @param iModel
     *            The custom model to show when the {@link FormComponent} has a
     *            FeedbackMessage
     */
    public ValidationErrorLabel(String id, FormComponent<T> component, IModel<?> iModel) {
        super(id);
        this.component = component;
        this.text = iModel;
    }

    /**
     * Set the content of this ValidationErrorLabel, depending on if the component has
     * a FeedbackMessage.
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void onBeforeRender() {
        super.onBeforeRender();
        setDefaultModel(null);
        if ( (component != null && component.getFeedbackMessage() != null) || text != null) {
            if (text != null) {
                setDefaultModel(text);
            } else {
                setDefaultModel(new Model(component.getFeedbackMessage().getMessage()));
                component.getFeedbackMessage().markRendered();
            }
        } else {
            setDefaultModel(null);
        }
    }
    
    
    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        if ((component != null && component.getFeedbackMessage() != null) || text != null) {
            final AppendingStringBuffer buffer = new AppendingStringBuffer("<div class=\"bubble error\"><p>" + getDefaultModelObjectAsString() + "</p></div>");
            replaceComponentTagBody(markupStream, openTag, buffer);
        }
    }
    
    /**
     * @inheritDoc
     * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        if (StringUtils.isEmpty(tag.getAttribute("id")) && !StringUtils.isEmpty(getMarkupId())) {
            tag.put("id", getMarkupId());
        }
    }
    
    public void setMessage(String message) {
        text = new Model<String>(message);
    }
}
