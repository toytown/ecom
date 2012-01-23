package com.ecom.web.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Session;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.Message;
import com.ecom.web.data.MessageDataProvider;
import com.ecom.web.main.EcomSession;

public class MessageInboxPage extends UserDashBoardPage {
	private static final long serialVersionUID = -520591566156931738L;

	private Set<String> selectedIds = new HashSet<String>();

	public MessageInboxPage() {

		EcomSession session = (EcomSession) Session.get();

		SortableDataProvider<Message> dataProvider = new MessageDataProvider(session.getUserId());

		final Form<Void> messageForm = new Form<Void>("messageForm") {

			private static final long serialVersionUID = 1L;

			public void onSubmit() {

				for (String id : selectedIds) {
					System.out.println(id);
				}
			}
		};


		final DataView<Message> dataView = new DataView<Message>("userMessageResultsView", dataProvider) {

			private static final long serialVersionUID = 6097489432223932704L;

			@Override
			protected void populateItem(Item<Message> item) {
				final Message msg = item.getModelObject();
				final String msgId = msg.getId().toString();

				item.add(new Check<String>("check", Model.of(msgId)));
				
				
				Link<Void> senderLink = new Link<Void>("senderLink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						PageParameters params = new PageParameters();
						params.set("MSG_ID", msgId);
						this.setResponsePage(MessageDetailPage.class, params);			
					}
					
				};
				Label sender = new Label("sender", Model.of(msg.getSender()));
				senderLink.add(sender);
				
				Label title = new Label("title", Model.of(msg.getTitle()));
				IModel<Date> dateModel = Model.of(msg.getSentTs());
				PatternDateConverter dateConverter = new PatternDateConverter("yyyy-mm-dd hh:mm:ss", false);
				DateLabel sentDate = new DateLabel("sentTs", dateModel, dateConverter);
				
				if (msg.isOpened()) {
					title.add(AttributeAppender.replace("class", "normal"));
					sender.add(AttributeAppender.replace("class", "normal"));
					sentDate.add(AttributeAppender.replace("class", "normal"));
				} else {
					title.add(AttributeAppender.replace("class", "highlight"));
					sender.add(AttributeAppender.replace("class", "highlight"));
					sentDate.add(AttributeAppender.replace("class", "highlight"));
				}
				

				item.add(senderLink);	
				item.add(title);
				item.add(sentDate);

			}
		};

		final CheckGroup<String> checkgroup = new CheckGroup<String>("group", selectedIds);
		checkgroup.add(new CheckGroupSelector("groupselector"));
		messageForm.add(checkgroup);
		
		checkgroup.add(new OrderByBorder("orderBySender", "sender", dataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged() {
				dataView.setCurrentPage(0);
			}
		});

		checkgroup.add(new OrderByBorder("orderByTitle", "title", dataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged() {
				dataView.setCurrentPage(0);
			}
		});

		checkgroup.add(new OrderByBorder("orderBySentTs", "sentTs", dataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged() {
				dataView.setCurrentPage(0);
			}
		});
		dataView.setItemsPerPage(5);
		checkgroup.add(dataView);
		final PagingNavigator pagingNavigator = new PagingNavigator("pagingNavigator", dataView);

		pagingNavigator.setVisible(true);
		pagingNavigator.setOutputMarkupId(true);
		checkgroup.add(pagingNavigator);
		add(messageForm);
	}

}
