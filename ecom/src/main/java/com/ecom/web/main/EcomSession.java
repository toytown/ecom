package com.ecom.web.main;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.User;
import com.ecom.repository.UserRepository;

public class EcomSession extends WebSession {

	private static final long serialVersionUID = 1L;
	private volatile boolean signedIn;
	private String userName = null;
	
	@SpringBean
	private UserRepository userRepository;
	
	public EcomSession(Request request) {
		super(request);
		Injector.get().inject(this);
	}

	public final boolean signIn(final String username, final String password) {
		User user = userRepository.findUserByUserNameAndPassword(username, password);
		if (user != null) {
			signedIn = true;
		}
		
		if (signedIn) {
			bind();
		}
		return signedIn;
	}
	
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
