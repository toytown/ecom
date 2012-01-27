package com.ecom.web.login;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.User;
import com.ecom.repository.UserRepository;
import com.ecom.web.main.GenericTemplatePage;

public class UserActivationPage extends GenericTemplatePage {

	private static final long serialVersionUID = -1940298512674779545L;
	
	@SpringBean
	private UserRepository userRepository;
	
	public UserActivationPage() {
		super();
		setStatelessHint(true);
	}

	public UserActivationPage(PageParameters pm) {
		super();
		setStatelessHint(true);		
		
		String userId = (String) pm.get("userId").toString();
		String activationCode = pm.get("activationCode").toString();
		
		if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(activationCode) ) {
			User newUser = userRepository.findOne(new ObjectId(userId));
			
			if (newUser != null && newUser.getActivationCode() != null && newUser.getActivationCode().equals(activationCode)) {
			    newUser.activate();
				userRepository.save(newUser);
				
			}
		}
	}	
}
