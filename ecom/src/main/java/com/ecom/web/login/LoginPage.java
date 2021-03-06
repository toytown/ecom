package com.ecom.web.login;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.user.EntriesPage;

public class LoginPage extends GenericTemplatePage {

    private static final long serialVersionUID = 6391262326443881229L;

    public LoginPage() {
        super();

        LoginRequest login = new LoginRequest();
        CompoundPropertyModel<LoginRequest> loginModel = new CompoundPropertyModel<LoginRequest>(login);
        StatelessForm<LoginRequest> loginForm = new StatelessForm<LoginRequest>("loginForm", loginModel);
        TextField<String> userName = new TextField<String>("userName");
        PasswordTextField password = new PasswordTextField("password");
        loginForm.add(userName);
        loginForm.add(password);

        BookmarkablePageLink<Void> newRegistrationLink = new BookmarkablePageLink<Void>("isNewRegistraion", RegistrationPage.class);
        loginForm.add(newRegistrationLink);

        EcomSession session = (EcomSession) Session.get();

        if (session.isSignedIn()) {
            setResponsePage(EntriesPage.class);
        }
        
        add(new SubmitLink("submitLogin", loginForm) {

            private static final long serialVersionUID = 1969220803824994712L;

            @Override
            public void onSubmit() {

                LoginRequest loginRequest = (LoginRequest) this.getForm().getDefaultModelObject();
                if (loginRequest != null && loginRequest.getUserName() != null) {
                    EcomSession session = (EcomSession) EcomSession.get();
                    if (session.signIn(loginRequest.getUserName(), loginRequest.getPassword())) {

                        if (!continueToOriginalDestination()) {
                            setResponsePage(getApplication().getHomePage());
                        }

                    } else {
                        error("User could not be authenticated due to invalid username or password");
                    }

                } else {
                    throw new RestartResponseAtInterceptPageException(LoginPage.class);
                }

            }

            @Override
            public void onError() {
                error("User could not be authenticated due to invalid username or password");
            }
        });

        add(new FeedbackPanel("feedback"));
        add(loginForm);
    }

}
