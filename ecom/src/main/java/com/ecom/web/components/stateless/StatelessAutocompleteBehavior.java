package com.ecom.web.components.stateless;

import java.util.Iterator;

import org.apache.wicket.Application;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.lang.Args;

public abstract class StatelessAutocompleteBehavior<T> extends StatelessAbstractAutoCompleteBehavior {

    private static final long serialVersionUID = 1L;

    private final IAutoCompleteRenderer<T> renderer;

    /**
     * Constructor
     * 
     * @param renderer
     *            renderer that will be used to generate output
     */
    public StatelessAutocompleteBehavior(final IAutoCompleteRenderer<T> renderer)
    {
        this(renderer, false);
    }

    /**
     * Constructor
     * 
     * @param renderer
     *            renderer that will be used to generate output
     * @param preselect
     *            highlight/preselect the first item in the autocomplete list automatically
     */
    public StatelessAutocompleteBehavior(final IAutoCompleteRenderer<T> renderer, final boolean preselect)
    {
        this(renderer, new AutoCompleteSettings().setPreselect(preselect));
    }

    /**
     * Constructor
     * 
     * @param renderer
     *            renderer that will be used to generate output
     * @param settings
     *            settings for the autocomplete list
     */
    public StatelessAutocompleteBehavior(final IAutoCompleteRenderer<T> renderer,
        final AutoCompleteSettings settings)
    {
        super(settings);

        this.renderer = Args.notNull(renderer, "renderer");
    }

    @Override
    protected final void onRequest(final String val, final RequestCycle requestCycle)
    {
        IRequestHandler target = new IRequestHandler()
        {
            public void respond(final IRequestCycle requestCycle)
            {
                WebResponse r = (WebResponse)requestCycle.getResponse();

                // Determine encoding
                final String encoding = Application.get()
                    .getRequestCycleSettings()
                    .getResponseRequestEncoding();

                r.setContentType("text/xml; charset=" + encoding);
                r.disableCaching();

                Iterator<T> comps = getChoices(val);
                int count = 0;
                renderer.renderHeader(r);
                while (comps.hasNext())
                {
                    final T comp = comps.next();
                    renderer.render(comp, r, val);
                    count += 1;
                }
                renderer.renderFooter(r, count);
            }

            public void detach(final IRequestCycle requestCycle)
            {
            }
        };

        requestCycle.scheduleRequestHandlerAfterCurrent(target);
    }

    /**
     * Callback method that should return an iterator over all possible choice objects. These
     * objects will be passed to the renderer to generate output. Usually it is enough to return an
     * iterator over strings.
     * 
     * @param input
     *            current input
     * @return iterator over all possible choice objects
     */
    protected abstract Iterator<T> getChoices(String input);

}
