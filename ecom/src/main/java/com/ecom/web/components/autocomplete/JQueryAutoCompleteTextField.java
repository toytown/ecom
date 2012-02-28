package com.ecom.web.components.autocomplete;

import java.util.List;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;


/**
 *
 * @author jeffrey
 */
public abstract class JQueryAutoCompleteTextField<T extends Object> extends TextField<T> {
    
    private final String jQuerySelector;

    public JQueryAutoCompleteTextField(String id, IModel<T> model, Class<T> type, String jQuerySelector) {
        super(id, model, type);
        this.jQuerySelector = jQuerySelector;
        common();
    }

    public JQueryAutoCompleteTextField(String id, IModel<T> model, String jQuerySelector) {
        super(id, model);
        this.jQuerySelector = jQuerySelector;
        common();
    }

    public JQueryAutoCompleteTextField(String id, Class<T> type, String jQuerySelector) {
        super(id, type);
        this.jQuerySelector = jQuerySelector;
        common();
    }

    public JQueryAutoCompleteTextField(String id, String jQuerySelector) {
        super(id);
        this.jQuerySelector = jQuerySelector;
        common();
    }

    private void common(){
        add(new AbstractJQueryAutocompleAjaxBehavior(jQuerySelector) {

            @Override
            public List<?> getMatches(String term) {
                return JQueryAutoCompleteTextField.this.getMatches(term);
            }
        });
    }
    
    public abstract List<?> getMatches(String term);
    
    
//    @Override
//    public boolean getStatelessHint() {
//        return true;
//    }
}