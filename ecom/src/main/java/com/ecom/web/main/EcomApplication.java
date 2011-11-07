package com.ecom.web.main;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.MountedMapper;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.ecom.common.utils.AppConfig;
import com.ecom.web.login.LoginPage;
import com.ecom.web.login.RegistrationPage;
import com.ecom.web.search.DetailViewPage;
import com.ecom.web.search.HomePage;
import com.ecom.web.search.SearchResultPage;

public class EcomApplication extends WebApplication {

	@SpringBean
	AppConfig appConfig;

	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));

		mountPage("/home", HomePage.class);
		mountPage("/details", DetailViewPage.class);
		mountPage("/login", LoginPage.class);
		mountPage("/registration", RegistrationPage.class);
		mountPage("/home/results", SearchResultPage.class);
		getRootRequestMapperAsCompound().add(new MountedMapper("/home", HomePage.class));
		getRootRequestMapperAsCompound().add(new MountedMapper("/home/results", SearchResultPage.class));
		getRootRequestMapperAsCompound().add(new MountedMapper("/login", LoginPage.class));
		getRootRequestMapperAsCompound().add(new MountedMapper("/details", DetailViewPage.class));
		getRootRequestMapperAsCompound().add(new MountedMapper("/registration", RegistrationPage.class));

		// disables echoing of wicket tags and their attributes to resulting html
		getMarkupSettings().setStripWicketTags(true);
		getApplicationSettings().setPageExpiredErrorPage(getHomePage());
		getMarkupSettings().setDefaultBeforeDisabledLink("");
		getMarkupSettings().setDefaultAfterDisabledLink("");

//		IAuthorizationStrategy authorizationStrategy = new SimplePageAuthorizationStrategy(UserDetailPage.class, LoginPage.class) {
//
//			@Override
//			protected boolean isAuthorized() {
//
//				return ((EcomSession)EcomSession.get()).isSignedIn();
//			}
//
//		};
//		
//		getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);		
		

	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	public Session newSession(Request req, Response resp) {
		return new EcomSession(req);

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
