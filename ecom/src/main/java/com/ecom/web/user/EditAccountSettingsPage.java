package com.ecom.web.user;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.User;
import com.ecom.repository.UserRepository;
import com.ecom.web.data.DetachableUserModel;
import com.ecom.web.main.EcomSession;

public class EditAccountSettingsPage extends UserDashBoardPage {


	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserRepository userRepository;
	
	public EditAccountSettingsPage() {
		super();
		
		EcomSession sess = (EcomSession) Session.get();
		String userId = sess.getUserId();
		DetachableUserModel user = new DetachableUserModel(userId);
		CompoundPropertyModel<User> userModel = new CompoundPropertyModel<User>(user);

		final Form<User> userEditForm = new Form<User>("userEditForm", userModel);

		TextField<String> userName = new TextField<String>("userName");
		userEditForm.add(userName);
		
		PasswordTextField password= new PasswordTextField("password");
		userEditForm.add(password);

		PasswordTextField password2= new PasswordTextField("password2");
		userEditForm.add(password2);
		
		EqualPasswordInputValidator passwordValidator = new EqualPasswordInputValidator(password, password2);
		userEditForm.add(passwordValidator);
		
		
		EmailTextField email = new EmailTextField("email");
		userEditForm.add(email);

		TextField<String> phone1 = new TextField<String>("phone1");
		userEditForm.add(phone1);

		TextField<String> phone2 = new TextField<String>("phone2");
		userEditForm.add(phone2);

		TextField<String> url = new TextField<String>("homePageURL");
		userEditForm.add(url);

		TextField<String> street = new TextField<String>("street");
		userEditForm.add(street);

		TextField<String> houseNo = new TextField<String>("houseNumber");
		userEditForm.add(houseNo);

		TextField<String> zip = new TextField<String>("zip");
		userEditForm.add(zip);

		TextField<String> city = new TextField<String>("city");
		userEditForm.add(city);

		Button editBtn = new Button("saveChanges", new Model<String>("Save")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				User user = (User) userEditForm.getDefaultModelObject();
				userRepository.save(user);
				setResponsePage(AccountPage.class);
			}
		};
		
		userEditForm.add(editBtn);		
		userEditForm.add(new FeedbackPanel("feedback"));
		add(userEditForm);
	}

}
