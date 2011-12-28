package com.ecom.web.components.validation;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class CustomBusyIndicator extends AjaxIndicatorAppender {

    private static final long serialVersionUID = 1L;

    private static final String WEP_INDICATOR_IMG = "busy.gif";

    public static final ResourceReference INDICATOR = new PackageResourceReference(CustomBusyIndicator.class, WEP_INDICATOR_IMG);

    public static final String MARKUP_ID = "busy-ajax-indicator";

    public CustomBusyIndicator() {
        super();
    }

    @Override
    public String getMarkupId() {
        return MARKUP_ID;
    }

    /**
     * @return url of the animated indicator image
     */
    @Override
    protected CharSequence getIndicatorUrl() {
        IRequestHandler handler = new ResourceReferenceRequestHandler(
                CustomBusyIndicator.INDICATOR);
        return RequestCycle.get().urlFor(handler);

    }

    @Override
    public void afterRender(final Component component)
    {
        super.afterRender(component);
        final Response r = component.getResponse();

        r.write("<span style=\"display:none;\" class=\"");
        r.write(getSpanClass());
        r.write("\" ");
        r.write("id=\"");
        r.write(getMarkupId());
        r.write("\">");
        r.write("<img src=\"");
        r.write(getIndicatorUrl());
        r.write("\" alt=\"\"/></span>");

    }

}
