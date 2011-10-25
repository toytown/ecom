package com.ecom.web.login;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.QObjectId;

import com.ecom.domain.QUser;
import com.ecom.domain.User;
import com.ecom.repository.UserRepository;
import com.ecom.web.main.GenericTemplatePage;

public class ActivationPage extends GenericTemplatePage {

	private static final long serialVersionUID = -1940298512674779545L;
	
	@SpringBean
	private UserRepository userRepository;
	
	public ActivationPage() {
		super();
		setStatelessHint(true);
	}

	public ActivationPage(PageParameters pm) {
		super();
		setStatelessHint(true);		
		
		String userId = (String) pm.get("userId").toString();
		String activationCode = pm.get("activationCode").toString();
		
		if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(activationCode) ) {
			QUser user = new QUser("user");
			QObjectId paramUserId = new QObjectId(userId);
			User domainUser = userRepository.findOne(user.id.eq(paramUserId).and(user.activationCode.eq(activationCode)));
			
			if (domainUser != null) {
				domainUser.activate();
				userRepository.save(domainUser);
				
			}
		}
	}	
}
