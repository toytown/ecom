package com.ecom.web.login;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;

import com.ecom.domain.User;
import com.ecom.web.main.GenericTemplatePage;

public class RegistrationPage extends GenericTemplatePage {

	private static final long serialVersionUID = 1L;

	public RegistrationPage() {
		super();

		final User user = new User();

		CompoundPropertyModel<User> userModel = new CompoundPropertyModel<User>(user);
		
		StatelessForm<User> userRegistrationForm = new StatelessForm<User>("userRegistrationForm", userModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public final void onSubmit() {


			}

		};



		TextField<String> titleText = new TextField<String>("title");
		userRegistrationForm.add(titleText);

		TextField<String> firstNameText = new TextField<String>("firstName");
		userRegistrationForm.add(firstNameText);

		TextField<String> lastNameText = new TextField<String>("lastName");
		userRegistrationForm.add(lastNameText);

		TextField<String> companyNameText = new TextField<String>("companyName");
		userRegistrationForm.add(companyNameText);

		TextField<String> userNameText = new TextField<String>("userName");
		userRegistrationForm.add(userNameText);

		PasswordTextField passwordText = new PasswordTextField("password");
		userRegistrationForm.add(passwordText);

		RadioGroup<String> group = new RadioGroup<String>("userCategory");
		//group.setDefaultModel(userModel);
		userRegistrationForm.add(group);
		
		ListView<String> categories = new ListView<String>("categories", User.USER_CATEGORIES) {
			private static final long serialVersionUID = -5614607677906229191L;

			@Override
			protected void populateItem(ListItem<String> item) {
				item.add(new Radio<String>("category", item.getModel()));
			};
		}.setReuseItems(true);
		group.add(categories);
		
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
		

		userRegistrationForm.add(new Button("register"));
		
		add(userRegistrationForm);
		
	}

	
}
