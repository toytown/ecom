package com.ecom.web.login;

import java.util.Date;
import java.util.UUID;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.User;
import com.ecom.repository.UserRepository;
import com.ecom.web.components.captcha.Captcha;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.user.UserDashBoardPage;

public class RegistrationPage extends GenericTemplatePage {

	private static final long serialVersionUID = 1L;
    
	@SpringBean
	private UserRepository userRepository;
	

	
	public RegistrationPage() {
		super();

		final User user = new User();

		CompoundPropertyModel<User> userModel = new CompoundPropertyModel<User>(user);

		Form<User> userRegistrationForm = new Form<User>("userRegistrationForm", userModel) {

			private static final long serialVersionUID = 1L;

			@Override
			public final void onSubmit() {
				User user = this.getModelObject();
				user.setActivationCode(UUID.randomUUID().toString());
				user.setInsertTs(new Date());
				user.setPassword(user.getPassword());
				user.setPassword2(user.getPassword2());
				userRepository.save(user);
				setResponsePage(UserDashBoardPage.class);
			}

		};

		userRegistrationForm.add(new FeedbackPanel("feedback"));
		/*
		TextField<String> titleText = new TextField<String>("title");
		userRegistrationForm.add(titleText);

		TextField<String> firstNameText = new TextField<String>("firstName");
		userRegistrationForm.add(firstNameText);

		TextField<String> lastNameText = new TextField<String>("lastName");
		userRegistrationForm.add(lastNameText);

		TextField<String> companyNameText = new TextField<String>("companyName");
		userRegistrationForm.add(companyNameText);
        */
		
		TextField<String> userNameText = new TextField<String>("userName");
		userRegistrationForm.add(userNameText);

		PasswordTextField passwordText = new PasswordTextField("password");
		userRegistrationForm.add(passwordText);

        PasswordTextField passwordText2 = new PasswordTextField("password2");
        userRegistrationForm.add(passwordText2);

        /*
		IModel<List<Category>> loadableCategories = new LoadableDetachableModel<List<Category>>() {
			private static final long serialVersionUID = 7538634096362947517L;

			@Override
			protected List<Category> load() {
				return User.getCategories();
			}
		};


		user.setUserCategory(User.defaultCategory);
		DropDownChoice<Category> userCategories = new DropDownChoice<Category>("categories", new Model<Category>(user.getUserCategory()), loadableCategories);

		userCategories.setChoiceRenderer(new ChoiceRenderer<Category>("name", "id"));

		userRegistrationForm.add(userCategories);
		
		TextField<String> cityText = new TextField<String>("city");
		userRegistrationForm.add(cityText);

		TextField<String> zipText = new TextField<String>("zip");
		userRegistrationForm.add(zipText);

		TextField<String> streetText = new TextField<String>("street");
		userRegistrationForm.add(streetText);

		TextField<String> houseNumberText = new TextField<String>("houseNumber");
		userRegistrationForm.add(houseNumberText);

		TextField<String> emailText = new TextField<String>("email");
		userRegistrationForm.add(emailText);

		TextField<String> faxText = new TextField<String>("fax");
		userRegistrationForm.add(faxText);

		TextField<String> mobileText = new TextField<String>("mobile");
		userRegistrationForm.add(mobileText);

		TextField<String> phone1 = new TextField<String>("phone1");
		userRegistrationForm.add(phone1);

		TextField<String> homePageURLText = new TextField<String>("homePageURL");
		userRegistrationForm.add(homePageURLText);
		*/
		
		userRegistrationForm.add(new Button("register"));
		userRegistrationForm.add(new Captcha("captcha"));
		
		add(userRegistrationForm);

	}

}
