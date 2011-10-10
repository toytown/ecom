package com.ecom.web.main;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.ecom.web.search.HomePage;

public class EcomApplication extends WebApplication {

    @Override
    public void init() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));        
    }
    
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	public void renderHead(IHeaderResponse response) {
		response.renderCSSReference("css/style.css");
	}
}
