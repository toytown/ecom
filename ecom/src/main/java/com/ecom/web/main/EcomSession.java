package com.ecom.web.main;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.SearchRequest;
import com.ecom.domain.User;
import com.ecom.service.interfaces.UserService;

public class EcomSession extends WebSession {

	private static final long serialVersionUID = 1L;
	private volatile boolean signedIn;
	private String userName = null;
	private String userId = null;
	private ConcurrentMap<String, String> bookMarkMap =  new ConcurrentHashMap<String, String>();
	private SearchRequest req = null; 
	
	@SpringBean
	private UserService userService;

	public EcomSession(Request request) {
		super(request);
		Injector.get().inject(this);
	}

	public final synchronized void addToFavourites(String realStateId, String title) {
		if (this.isTemporary()) {
			bind();
		}
		
		if (!bookMarkMap.containsKey(realStateId) && title != null) {
			this.getId();
			bookMarkMap.put(realStateId, title);
			userService.saveMarkedItems(realStateId, this.getUserId());
		}
	}
	
	public final synchronized void clearFavourites() {
		bookMarkMap.clear();
	}
	
	public ConcurrentMap<String, String> getFavourites() {
		return bookMarkMap;
	}
	
	public final boolean signIn(final String username, final String password) {
		User user = userService.findUserByUserNameAndPassword(username, password);
		if (user != null) {
			signedIn = true;
		}

		if (signedIn) {
		    //prevents session fixation attack
		    this.replaceSession();
			
			setUserId(user.getId().toString());
			setUserName(user.getUserName());
			user.setLastLoginTs(new Date());
			userService.save(user);
		}
		return signedIn;
	}

	public final boolean isSignedIn() {
		return signedIn;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void signOut() {
		super.invalidate();
		signedIn = false;
		userName = null;
		userId = null;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void saveSearchRequest(SearchRequest req) {
        if (this.isTemporary()) {
            bind();
        }	    
	    this.req = req;
	}
	
	
    public SearchRequest getSearchRequest() {
        return this.req;
    }	
}
