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
import com.ecom.web.login.LoginPage;
import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;

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

                @Override
                protected User load() {
                    QUser user = new QUser("user");
                    return userRepository.findOne(user.userName.eq(userName));
                }
            };
            
	        Link<User> messagesLink = new Link<User>("messagesLink", userModel) {

                @Override
                public void onClick() {
                    PageParameters params = new PageParameters();
                    params.add("userId", this.getModel().getObject().getId().toString());
                    setResponsePage(InboxPage.class, params);
                }
	            
	        };
	        
	        messagesLink.add(new ContextImage("msgIcon", "images/usericons/message.png"));
	        add(messagesLink);
	    } else {
	        setResponsePage(LoginPage.class);
	    }
	}
	
	 
}
