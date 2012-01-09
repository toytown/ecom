package com.ecom.web.user;

import java.util.Arrays;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.User;
import com.ecom.domain.UserStatus;
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
		
		TextField<String> password= new TextField<String>("password");
		userEditForm.add(password);
		
		UserStatus status = UserStatus.getUserStatusById(user.getObject().getStatus());
		EnumChoiceRenderer<UserStatus> userStatusEnum = new EnumChoiceRenderer<UserStatus>(this);
		final IModel<UserStatus> userStatusSel = new Model<UserStatus>(status);
		DropDownChoice<UserStatus> userStatuses = new DropDownChoice<UserStatus>("userStatus", userStatusSel, Arrays.asList(UserStatus.values()), userStatusEnum);
		userEditForm.add(userStatuses);
		
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
				user.setStatus(UserStatus.getStatusId(userStatusSel.getObject()));
				userRepository.save(user);
				setResponsePage(AccountPage.class);
			}
		};
		
		userEditForm.add(editBtn);		
		add(userEditForm);
	}

}
