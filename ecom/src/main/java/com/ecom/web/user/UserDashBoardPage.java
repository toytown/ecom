package com.ecom.web.user;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.QUser;
import com.ecom.domain.User;
import com.ecom.repository.UserRepository;
import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.search.HomePage;

public class UserDashBoardPage extends GenericTemplatePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8655494738660715236L;

	@SpringBean
	private UserRepository userRepository;
	
	public UserDashBoardPage() {
	
	    
	    EcomSession sess = (EcomSession) Session.get();
	    final String userName = sess.getUserName();
	    
	    if (sess.isSignedIn()) {
	        IModel<User> userModel = new LoadableDetachableModel<User>() {

				private static final long serialVersionUID = 1L;

					@Override
                protected User load() {
                    QUser user = new QUser("user");
                    return userRepository.findOne(user.userName.eq(userName));
                }
            };
 
  	        Link<User> editLink = new Link<User>("editLink", userModel) {

  				private static final long serialVersionUID = 1L;

  				@Override
                public void onClick() {
                    PageParameters params = new PageParameters();
                    params.add("userId", this.getModel().getObject().getId().toString());
                    setResponsePage(EntriesPage.class);
                }
  	            
  	        };
  	        
 	        Link<User> accountLink = new Link<User>("accountLink", userModel) {

				private static final long serialVersionUID = 1L;

				@Override
              public void onClick() {
                  setResponsePage(AccountPage.class);
              }
	            
	        };
	        
	        Link<User> messagesLink = new Link<User>("messagesLink", userModel) {

				private static final long serialVersionUID = 1L;

					@Override
                public void onClick() {
                    PageParameters params = new PageParameters();
                    params.add("userId", this.getModel().getObject().getId().toString());
                    setResponsePage(MessageInboxPage.class, params);
                }
	            
	        };
	        
	        accountLink.add(new ContextImage("accountIcon", "images/usericons/user.png"));
	        editLink.add(new ContextImage("editIcon", "images/usericons/edit.png"));
	        messagesLink.add(new ContextImage("msgIcon", "images/usericons/message.png"));
	        
	        add(accountLink);
	        add(messagesLink);
	        add(editLink);
	    } else {
	        setResponsePage(HomePage.class);
	    }
	}
	
	 
}
