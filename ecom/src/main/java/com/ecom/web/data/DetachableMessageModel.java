package com.ecom.web.data;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.Message;
import com.ecom.repository.MessageRepository;

public class DetachableMessageModel extends LoadableDetachableModel<Message> {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private MessageRepository messageRepository;

	private String messageId;

	public DetachableMessageModel(String id) {
		this.messageId = id;
		Injector.get().inject(this);
	}

	@Override
	protected Message load() {
		return messageRepository.findOne(new ObjectId(messageId));
	}

}
