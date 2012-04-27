package com.ecom.web.main;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.devutils.stateless.StatelessChecker;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.ecom.common.utils.AppConfig;
import com.ecom.web.components.image.EcomImageResouceReference;
import com.ecom.web.login.LoginPage;
import com.ecom.web.login.RegistrationPage;
import com.ecom.web.login.RetrievePasswordPage;
import com.ecom.web.search.DetailViewPage;
import com.ecom.web.search.SearchResultPage;
import com.ecom.web.upload.AddRealStateInfoPage;
import com.ecom.web.user.LogoutPage;
import com.ecom.web.user.UserDashBoardPage;
import com.ecom.web.utils.SecurePage;

public final class EcomApplication extends WebApplication {

    private static final String GOOGLE_MAPS_API_KEY_PARAM = "GoogleMapsAPIkey";

    @SpringBean
    private AppConfig appConfig;

    private ServerGeocoder serverGeocoder = null;

    @Override
    public final void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        serverGeocoder = new ServerGeocoder(getGoogleMapsAPIkey());
        mountPage("/home", HomePage.class);
        mountPage("/details", DetailViewPage.class);
        mountPage("/home/login", LoginPage.class);
        mountPage("/home/retrievePassword", RetrievePasswordPage.class);
        mountPage("/home/registration", RegistrationPage.class);
        mountPage("/home/results", SearchResultPage.class);
        mountPage("/home/dashboard", UserDashBoardPage.class);
        mountPage("/home/addRealState", AddRealStateInfoPage.class);
        mountPage("/home/logout", LogoutPage.class);

        mountResource("/imagerepo/${id}", new EcomImageResouceReference());
        this.getComponentPostOnBeforeRenderListeners().add(new StatelessChecker());

        // disables echoing of wicket tags and their attributes to resulting html
        getMarkupSettings().setStripWicketTags(true);
        getApplicationSettings().setPageExpiredErrorPage(getHomePage());
        getMarkupSettings().setDefaultBeforeDisabledLink("");
        getMarkupSettings().setDefaultAfterDisabledLink("");

        //getDebugSettings().setDevelopmentUtilitiesEnabled(true);
        getApplicationSettings().setUploadProgressUpdatesEnabled(true);
        //getComponentPreOnBeforeRenderListeners().add(new StatelessChecker());
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

        // Register the authorization strategy
        /*
        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy() {
        	public boolean isActionAuthorized(Component component, Action action) {
        		// authorize everything
        		return true;
        	}

        	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {

        		if (!SecurePage.class.isAssignableFrom(componentClass)) {
        			return true;
        		} else {
        			if (((EcomSession) Session.get()).isSignedIn() == false) {
        				throw new RestartResponseAtInterceptPageException(LoginPage.class);
        			}
        			
        			return true;
        		}

        	}
        });
        */
        
        SimplePageAuthorizationStrategy authorizationStrategy = new SimplePageAuthorizationStrategy(
                SecurePage.class, LoginPage.class) {
            
            protected boolean isAuthorized() {
                // Authorize access based on user authentication in the session
                return (((EcomSession) Session.get()).isSignedIn());
            }
        };

        getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);
    }

    @Override
    public final Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    public final Session newSession(Request req, Response resp) {
        return new EcomSession(req);

    }

    public void renderHead(IHeaderResponse response) {
        response.renderCSSReference("css/style.css");
    }

    @Override
    public final RuntimeConfigurationType getConfigurationType() {
        if (appConfig != null) {
            final String env = appConfig.getEnv();
            return env.equalsIgnoreCase("dev") ? RuntimeConfigurationType.DEVELOPMENT : RuntimeConfigurationType.DEPLOYMENT;
        }
        return super.getConfigurationType();
    }

    public static final EcomApplication get() {
        return (EcomApplication) Application.get();
    }

    public final String getGoogleMapsAPIkey() {
        String googleMapsAPIkey = getInitParameter(GOOGLE_MAPS_API_KEY_PARAM);
        if (googleMapsAPIkey == null) {
            throw new WicketRuntimeException("There is no Google Maps API key configured in the " + "deployment descriptor of this application.");
        }
        return googleMapsAPIkey;
    }

    public final ServerGeocoder getServerGeocoder() {
        return serverGeocoder;
    }

}
