package com.ecom.web.user;

import java.util.Arrays;

import org.apache.wicket.Session;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.ecom.domain.User;
import com.ecom.domain.UserType;
import com.ecom.web.data.DetachableUserModel;
import com.ecom.web.main.EcomSession;

public class AccountPage extends UserDashBoardPage {

	private static final long serialVersionUID = 1L;


	public AccountPage() {
		super();

		EcomSession sess = (EcomSession) Session.get();
		String userId = sess.getUserId();
		DetachableUserModel user = new DetachableUserModel(userId);
		CompoundPropertyModel<User> userModel = new CompoundPropertyModel<User>(user);

		this.setDefaultModel(userModel);

		UserType userType = UserType.getUserTypeById(user.getObject().getUserType());
		EnumChoiceRenderer<UserType> userTypeEnum = new EnumChoiceRenderer<UserType>(this);
		IModel<UserType> userTypeSel = new Model<UserType>(userType);
		
		DropDownChoice<UserType> userCategories = new DropDownChoice<UserType>("userType", userTypeSel, Arrays.asList(UserType.values()), userTypeEnum);
		//add(userCategories);
		userCategories.setEnabled(false);

		Label userCategory = new Label("userType", userTypeEnum.getDisplayValue(userType).toString());
		add(userCategory);
		
		DateLabel lastLogin = new DateLabel("lastLoginTs", new PatternDateConverter("yyyy-MM-dd hh:mm:ss", true));
		add(lastLogin);
		
		DateLabel activeDate = new DateLabel("activationTs", new PatternDateConverter("yyyy-MM-dd hh:mm:ss", true));
		add(activeDate);

		
		TextField<String> firstName = new TextField<String>("firstName");
		add(firstName);
		firstName.setEnabled(false);
		
		TextField<String> lastName = new TextField<String>("lastName");
		add(lastName);
		lastName.setEnabled(false);
		
		TextField<String> userName = new TextField<String>("userName");
		add(userName);
		userName.setEnabled(false);
		
		TextField<String> password = new TextField<String>("password");
		add(password);
		password.setEnabled(false);
		

		
		EmailTextField email = new EmailTextField("email");
		add(email);
		email.setEnabled(false);
		
		TextField<String> phone1 = new TextField<String>("phone1");
		add(phone1);
		phone1.setEnabled(false);
		
		TextField<String> phone2 = new TextField<String>("phone2");
		add(phone2);
		phone2.setEnabled(false);

		TextField<String> url = new TextField<String>("homePageURL");
		add(url);
		url.setEnabled(false);
		
		TextField<String> street = new TextField<String>("street");
		add(street);
		street.setEnabled(false);
		
		TextField<String> houseNo = new TextField<String>("houseNumber");
		add(houseNo);
		houseNo.setEnabled(false);
		
		TextField<String> zip = new TextField<String>("zip");
		add(zip);
		zip.setEnabled(false);
		
		TextField<String> city = new TextField<String>("city");
		add(city);
		city.setEnabled(false);
		
		Link<String> editBtn = new Link<String>("editSetting", new Model<String>("Edit Settings")) {
			
			private static final long serialVersionUID = 1L;

			public void onClick() {
				setResponsePage(EditAccountSettingsPage.class);
			}
		};
		
		add(editBtn);
		
	}

}
