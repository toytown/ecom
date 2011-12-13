package com.ecom.web.components.map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class GoogleMapBehaviour extends Behavior {
    
    private static final JavaScriptResourceReference GMAP_JS = new JavaScriptResourceReference(GoogleMapBehaviour.class, "js/gmap3.min.js");

    private boolean renderJqueryReference = true;
    private String address = "";
    
    public GoogleMapBehaviour(String address) {
        this.address = address;
    }
    

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.renderJavaScriptReference(GMAP_JS);
    }


    @Override
    public void afterRender(Component component) {
        super.afterRender(component);
        Response response = RequestCycle.get().getResponse();
        response.write(getJavaScript("Munich"));
    }    
    
    
    private CharSequence getJavaScript(String address) {
        StringBuilder gmapConfig = new StringBuilder();
        gmapConfig.append("<script type=\"text/javascript\">\n");
        gmapConfig.append("$('#map_canvas').gmap3();");
        gmapConfig.append("</script>\n");
        return gmapConfig.toString();
    }
    
    /**
     * Gets renderJqueryReference.
     * 
     * @return renderJqueryReference
     */
    public boolean isRenderJqueryReference() {
        return renderJqueryReference;
    }

    /**
     * Sets renderJqueryReference.
     * 
     * @param renderJqueryReference
     *            renderJqueryReference
     */
    public void setRenderJqueryReference(boolean renderJqueryReference) {
        this.renderJqueryReference = renderJqueryReference;
    }    
}
