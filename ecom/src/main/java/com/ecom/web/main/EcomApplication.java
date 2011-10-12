package com.ecom.web.main;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.ecom.common.utils.AppConfig;
import com.ecom.web.search.HomePage;

public class EcomApplication extends WebApplication {

    @SpringBean
    AppConfig appConfig;
    
    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        // disables echoing of wicket tags and their attributes to resulting html
        getMarkupSettings().setStripWicketTags(true);
        getApplicationSettings().setPageExpiredErrorPage(getHomePage());
        getMarkupSettings().setDefaultBeforeDisabledLink("");
        getMarkupSettings().setDefaultAfterDisabledLink("");        
    }
    
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	public void renderHead(IHeaderResponse response) {
		response.renderCSSReference("css/style.css");
	}
	
    @Override
    public RuntimeConfigurationType getConfigurationType() {
        if (appConfig != null) {
            final String env = appConfig.getEnv();
            return env.equalsIgnoreCase("dev") ? RuntimeConfigurationType.DEVELOPMENT : RuntimeConfigurationType.DEPLOYMENT;
        }
        return super.getConfigurationType();
    }	
}
