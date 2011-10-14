package com.ecom.web.components.stateless;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class StatelessAjaxFormComponentUpdatingBehavior
        extends AjaxFormComponentUpdatingBehavior {

    private static final long serialVersionUID = -286307141298283926L;

    /**
    * @param event
    */
    public StatelessAjaxFormComponentUpdatingBehavior(final String event) {
        super(event);
    }

    /**
    * Adding parameters the generated URL. This preferably would be handled by
    * the {@link StatelessWebRequestCodingStrategy}, but the frameworks
    * currently is lacking a way to pass the parameters to that class.
    *
    *
    * @see AbstractAjaxBehavior#getCallbackUrl()
    */
    @Override
    public CharSequence getCallbackUrl() {
        final Url url = Url.parse(super.getCallbackUrl().toString());
        final PageParameters params = getPageParameters();

        return StatelessEncoder.mergeParameters(url, params).toString();
    }

    protected abstract PageParameters getPageParameters();

    /**
    * @return always {@literal true}
    */
    @Override
    public boolean getStatelessHint(final Component component) {
        return true;
    }
}
