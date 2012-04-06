package com.ecom.web.user;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.ecom.domain.User;
import com.ecom.web.login.LoginPage;
import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.utils.SecurePage;


public class UserDashBoardPage extends GenericTemplatePage implements SecurePage {


	private static final long serialVersionUID = 8655494738660715236L;


	public UserDashBoardPage() {

		EcomSession sess = (EcomSession) Session.get();

		if (!sess.isSignedIn()) {
			setResponsePage(LoginPage.class);
		}
		
		BookmarkablePageLink<User> editLink = new BookmarkablePageLink<User>("editLink", EntriesPage.class);
		BookmarkablePageLink<User> accountLink = new BookmarkablePageLink<User>("accountLink", AccountPage.class);
		BookmarkablePageLink<User> messagesLink = new BookmarkablePageLink<User>("messagesLink", MessageInboxPage.class);


		accountLink.add(new ContextImage("accountIcon", "images/usericons/user.png"));
		editLink.add(new ContextImage("editIcon", "images/usericons/edit.png"));
		messagesLink.add(new ContextImage("msgIcon", "images/usericons/message.png"));

		add(accountLink);
		add(messagesLink);
		add(editLink);

	}

}
