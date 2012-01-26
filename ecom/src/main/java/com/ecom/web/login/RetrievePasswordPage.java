package com.ecom.web.login;

import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.service.interfaces.UserService;
import com.ecom.web.components.captcha.Captcha;
import com.ecom.web.main.GenericTemplatePage;



public class RetrievePasswordPage extends GenericTemplatePage {


    private String userNameOrEmail;


    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }


    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
    }


    @SpringBean
    private UserService userService;
    
    
    public RetrievePasswordPage() {
        super();
        
        TextField<String> userNameOrEmail = new TextField<String>("userNameOrEmail");

        StatelessForm<String> passwordRetrivalForm = new StatelessForm<String>("passwordRetrievalForm") {
            
            @Override
            public void onSubmit() {
                String userNameOrPassword = this.getDefaultModelObjectAsString();
                
                userService.retrivePassword(userNameOrPassword, false);
            }
        };

        
        passwordRetrivalForm.add(userNameOrEmail);
        passwordRetrivalForm.add(new Captcha("captcha"));
        
        add(passwordRetrivalForm);
    }


  
}
