package com.ecom.web.user;

import org.apache.wicket.Session;

import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.main.HomePage;

public class LogoutPage extends GenericTemplatePage {

	private static final long serialVersionUID = -9158910886220142083L;

	public LogoutPage() {
		super();
		EcomSession session = (EcomSession) Session.get();
		session.signOut();
		setResponsePage(HomePage.class);
	}


}
