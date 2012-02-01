package com.ecom.web.login;

import org.apache.commons.lang3.StringUtils;
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
        userNameOrEmail.setRequired(true);
        
        StatelessForm<Void> passwordRetrivalForm = new StatelessForm<Void>("passwordRetrievalForm") {
            
			private static final long serialVersionUID = 1L;

				@Override
            public void onSubmit() {
                String userNameOrPassword = userNameOrEmail.getDefaultModelObjectAsString();
                if (!StringUtils.isEmpty(userNameOrPassword)) {
                    boolean userRetrieved = userService.retriveAndSendNewPassword(userNameOrPassword);
                    if (!userRetrieved) {
                        error(getLocalizer().getString("err_no_user", this));
                    } else {
                        info(getLocalizer().getString("info_reset_passwd", this));
                    }
                } 
            }
        };

        passwordRetrivalForm.add(new FeedbackPanel("feedback"));
        passwordRetrivalForm.add(userNameOrEmail);
        add(passwordRetrivalForm);
    }


  
}
