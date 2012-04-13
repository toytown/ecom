package com.ecom.web.data;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ecom.domain.Message;
import com.ecom.domain.QMessage;
import com.ecom.repository.MessageRepository;

public class MessageDataProvider extends SortableDataProvider<Message> {

	private static final long serialVersionUID = 431164733248836183L;

	public static final int PAGE_SIZE = 15;

	public static final Sort DEFAULT_SORT = new Sort(Direction.DESC, "sentTs");

	private transient PageRequest req = new PageRequest(0, PAGE_SIZE, DEFAULT_SORT);

	private String receiver;

	@SpringBean
	private MessageRepository messageRepository;

	public MessageDataProvider(String receiver) {
		Injector.get().inject(this);
		this.receiver = receiver;
		setSort("sentTs", SortOrder.DESCENDING);
	}

	@Override
	public Iterator<? extends Message> iterator(int first, int count) {
		Iterator<Message> iter = null;

		if (!StringUtils.isEmpty(this.receiver)) {
			QMessage messageQuery = new QMessage("message");

			SortParam sortParam = this.getSort();
			Sort sort = null;

			if (sortParam.getProperty().equalsIgnoreCase("sentTs")) {
				sort = new Sort(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "sentTs");
			}

			if (sortParam.getProperty().equalsIgnoreCase("senderEmail")) {
				sort = new Sort(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "senderEmail");
			}

			req = new PageRequest((first + 1) / PAGE_SIZE, count, sort);
			iter = messageRepository.findAll(messageQuery.receiver.eq(this.receiver), req).iterator();

		}

		return iter;

	}

	@Override
	public int size() {
		QMessage messageQuery = new QMessage("message");
		return Long.valueOf(messageRepository.count(messageQuery.receiver.eq(this.receiver))).intValue();
	}

	@Override
	public IModel<Message> model(final Message object) {
		IModel<Message> realStateModel = new LoadableDetachableModel<Message>() {

			private static final long serialVersionUID = 1L;

			protected Message load() {
				return messageRepository.findOne(object.getId());
			}
		};

		return realStateModel;
	}

}
