package com.ecom.web.components.autocomplete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 *
 * @author jeffrey
 */
public abstract class AbstractJQueryAutocompleAjaxBehavior extends AbstractAjaxBehavior {

    // The jQuery selector to be used by the jQuery ready handler that registers the autocomplete behavior
    final private String jQuerySelector;

    /**
     * Constructor
     * @param jQuerySelector - a string containing the jQuery selector
     * for the target html element (<input type='text'... of the jQuery UI
     * Autocomplete component
     */
    public AbstractJQueryAutocompleAjaxBehavior(String jQuerySelector) {
        super();
        this.jQuerySelector = jQuerySelector;
    }

    /**
     * Contributes a jQuery ready handler that registers autocomplete
     * behavior for the html element represented by the selector.
     *
     * The generation of the ready handler uses interpolation, applying
     * the jQuery selector and the variable name of the return call
     * back url.
     *
     * @param component
     * @param response
     */
    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        Map<String, CharSequence> map = new HashMap<String, CharSequence>(2);
        map.put("selector", jQuerySelector);
        map.put("callbackUrl", getCallbackUrl());
        PackageTextTemplate packageTextTemplate = new PackageTextTemplate(getClass(), "autocomplete.js", "text/javascript");
        String resource = packageTextTemplate.asString(map);
        response.renderJavaScript(resource, jQuerySelector);
    }

    @Override
    public void onRequest() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("ajax request received");

        RequestCycle requestCycle = RequestCycle.get();
        Request request = requestCycle.getRequest();
        IRequestParameters irp = request.getRequestParameters();
        StringValue term = irp.getParameterValue("term");
        List<?> matches = getMatches(term.toString());
        String json = convertListToJson(matches);
        requestCycle.scheduleRequestHandlerAfterCurrent(new TextRequestHandler("application/json", "UTF-8", json));
    }

    public abstract List<?> getMatches(String term);
    
    /*
     * Convert List to json object.
     *
     * Dependency on google-gson library which is
     * available at http://code.google.com/p/google-gson/
     * and which must be on your classpath when using this
     * library.
     */
    private String convertListToJson(List<?> matches) {
        Gson gson = new Gson();
        String json = gson.toJson(matches);
        return json;
    }

    @Override
    public boolean getStatelessHint(Component c) {
        return true;
    }
    
}
