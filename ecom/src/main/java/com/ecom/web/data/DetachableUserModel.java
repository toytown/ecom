package com.ecom.web.data;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.User;
import com.ecom.repository.UserRepository;

public class DetachableUserModel extends LoadableDetachableModel<User>{

	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserRepository userRepository;

	private ObjectId userId;

	public DetachableUserModel(String userIdStr) {
		super();
		Injector.get().inject(this);
		userId = new ObjectId(userIdStr);
	}

	@Override
	protected User load() {
		return userRepository.findOne(userId);
	}	
}
