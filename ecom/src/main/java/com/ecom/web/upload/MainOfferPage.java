package com.ecom.web.upload;

import org.apache.wicket.markup.html.link.StatelessLink;

import com.ecom.domain.OffererType;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.utils.SecurePage;

public class MainOfferPage extends GenericTemplatePage implements SecurePage {

	private static final long serialVersionUID = -7494833509125509188L;

	public MainOfferPage() {

		setStatelessHint(true);

		add(new StatelessLink<Void>("private") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
			    AddRealStateInfoPage addRealStatePage = new AddRealStateInfoPage(OffererType.Private);
				setResponsePage(addRealStatePage);
				
			}
			
		});
		add(new StatelessLink<Void>("business") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
                AddRealStateInfoPage addRealStatePage = new AddRealStateInfoPage(OffererType.Business);
                setResponsePage(addRealStatePage);
				
			}
			
		});
	}

}
