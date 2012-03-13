package com.ecom.web.components.autocomplete;

import java.util.Iterator;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.StringAutoCompleteRenderer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import com.ecom.web.components.stateless.StatelessAutocompleteBehavior;

public abstract class StatelessAutocompleteTextField<T> extends TextField<T> {

    private static final long serialVersionUID = 1L;

    /** auto complete behavior attached to this textfield */
    private StatelessAutocompleteBehavior<T> behavior;

    /** renderer */
    private final IAutoCompleteRenderer<T> renderer;

    /** settings */
    private final AutoCompleteSettings settings;

    /**
     * @param id
     * @param type
     */
    public StatelessAutocompleteTextField(final String id, final Class<T> type)
    {
        this(id, null, type, new AutoCompleteSettings());
    }

    /**
     * Construct.
     * 
     * @param id
     * @param object
     * @param settings
     */
    public StatelessAutocompleteTextField(final String id, final IModel<T> object,
            final AutoCompleteSettings settings)
    {
        this(id, object, null, settings);
    }

    /**
     * @param id
     * @param object
     */
    public StatelessAutocompleteTextField(final String id, final IModel<T> object)
    {
        this(id, object, null, new AutoCompleteSettings());
    }

    /**
     * Construct.
     * 
     * @param id
     * @param settings
     */
    public StatelessAutocompleteTextField(final String id, final AutoCompleteSettings settings)
    {
        this(id, null, settings);
    }

    /**
     * @param id
     */
    public StatelessAutocompleteTextField(final String id)
    {
        this(id, null, new AutoCompleteSettings());
    }

    /**
     * @param id
     * @param renderer
     */
    public StatelessAutocompleteTextField(final String id, final IAutoCompleteRenderer<T> renderer)
    {
        this(id, (IModel<T>) null, renderer);
    }

    /**
     * @param id
     * @param type
     * @param renderer
     */
    public StatelessAutocompleteTextField(final String id, final Class<T> type,
            final IAutoCompleteRenderer<T> renderer)
    {
        this(id, null, type, renderer, new AutoCompleteSettings());
    }

    /**
     * @param id
     * @param model
     * @param renderer
     */
    public StatelessAutocompleteTextField(final String id, final IModel<T> model,
            final IAutoCompleteRenderer<T> renderer)
    {
        this(id, model, null, renderer, new AutoCompleteSettings());
    }

    /**
     * Construct.
     * 
     * @param id
     * @param model
     * @param type
     * @param renderer
     * @param settings
     */
    public StatelessAutocompleteTextField(final String id, final IModel<T> model, final Class<T> type,
            final IAutoCompleteRenderer<T> renderer, final AutoCompleteSettings settings)
    {
        super(id, model, type);
        this.renderer = renderer;
        this.settings = settings;
    }

    public StatelessAutocompleteTextField(final String id, final IModel<T> model, final Class<T> type,
            final AutoCompleteSettings settings)
    {
        this(id, model, type, StringAutoCompleteRenderer.INSTANCE, settings);
    }

    /**
     * Factory method for autocomplete behavior that will be added to this textfield
     * 
     * @param renderer
     *            auto complete renderer
     * @param settings
     *            auto complete settings
     * @return auto complete behavior
     */
    protected StatelessAutocompleteBehavior<T> newAutoCompleteBehavior(
            final IAutoCompleteRenderer<T> renderer, final AutoCompleteSettings settings)
    {
        return new StatelessAutocompleteBehavior<T>(renderer, settings)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected Iterator<T> getChoices(final String input)
            {
                return StatelessAutocompleteTextField.this.getChoices(input);
            }
            
            @Override
            public boolean getStatelessHint(Component c) {
                return true;
            }            
        };
    }



    @Override
    protected void onComponentTag(final ComponentTag tag)
    {
        super.onComponentTag(tag);

        // disable browser's autocomplete
        tag.put("autocomplete", "off");
    }

    /**
     * Callback method that should return an iterator over all possible assist choice objects. These
     * objects will be passed to the renderer to generate output. Usually it is enough to return an
     * iterator over strings.
     * 
     * @see AutoCompleteBehavior#getChoices(String)
     * 
     * @param input
     *            current input
     * @return iterator over all possible choice objects
     */
    protected abstract Iterator<T> getChoices(String input);

    @Override
    public boolean getStatelessHint() {
        return true;
    }
}
