package com.ecom.web.login;

import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.service.interfaces.UserService;
import com.ecom.web.main.GenericTemplatePage;



public class RetrievePasswordPage extends GenericTemplatePage {

	private static final long serialVersionUID = 5812646828401262200L;

	@SpringBean
    private UserService userService;

    private String userNameOrEmail;


    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }


    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
    }

    
    public RetrievePasswordPage() {
        super();
        
        final TextField<String> userNameOrEmail = new TextField<String>("userNameOrEmail", new PropertyModel<String>(this, "userNameOrEmail"));

        StatelessForm<Void> passwordRetrivalForm = new StatelessForm<Void>("passwordRetrievalForm") {
            
			private static final long serialVersionUID = 1L;

				@Override
            public void onSubmit() {
                String userNameOrPassword = userNameOrEmail.getDefaultModelObjectAsString();
                userService.retriveAndSendNewPassword(userNameOrPassword);
            }
        };

        passwordRetrivalForm.add(new FeedbackPanel("feedback"));
        passwordRetrivalForm.add(userNameOrEmail);
        add(passwordRetrivalForm);
    }


  
}
