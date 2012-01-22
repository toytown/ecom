package com.ecom.web.utils;

import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;

import com.ecom.web.login.LoginPage;
import com.ecom.web.main.EcomSession;

public class EcomAuthorizationStrategy extends SimplePageAuthorizationStrategy {

	public EcomAuthorizationStrategy() {
		super(SecurePage.class, LoginPage.class);
	}
	
	@Override
	protected boolean isAuthorized() {
		return ((EcomSession)Session.get()).isSignedIn();
	}

}
