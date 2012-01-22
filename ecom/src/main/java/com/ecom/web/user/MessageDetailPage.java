package com.ecom.web.user;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.Message;
import com.ecom.web.components.buttons.MiniBUtton;
import com.ecom.web.components.buttons.WepJsButton;
import com.ecom.web.data.DetachableMessageModel;

public class MessageDetailPage extends UserDashBoardPage {

	private static final long serialVersionUID = 1L;
	private String replyMessage = "";

	public MessageDetailPage(PageParameters params) {

		String messageId = params.get("MSG_ID").toString();

		DetachableMessageModel msgModel = new DetachableMessageModel(messageId);
		CompoundPropertyModel<Message> msg = new CompoundPropertyModel<Message>(msgModel);

		Label title = new Label("title", msg.bind("title"));
		add(title);

		Label sender = new Label("sender", msg.bind("sender"));
		add(sender);

		Date sentDate = (Date) msg.bind("sentTs").getObject();
		IModel<Date> sentDateModel = new Model<Date>(sentDate);
		DateLabel dateSent = new DateLabel("sentDate", sentDateModel, new PatternDateConverter("yyyy.MM.dd", true));

		add(dateSent);

		MultiLineLabel message = new MultiLineLabel("message", msg.bind("message"));
		add(message);

		setDefaultModel(msg);

		final Form<Void> replyForm = new Form<Void>("replyForm");
		replyForm.setOutputMarkupId(true);
		final TextArea<String> replyMessageText = new TextArea<String>("replyMessage", new PropertyModel<String>(this, "replyMessage"));

		replyForm.add(replyMessageText);

		replyForm.add(new WepJsButton("sendBtn", new Model<String>("send")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				String replyMsg = replyMessageText.getDefaultModelObjectAsString();
				if (!StringUtils.isEmpty(replyMsg)) {
					System.out.println(" " + replyMsg);
				}
			}

		});
		replyForm.setVisible(false);

		MiniBUtton<String> replyBtn = new MiniBUtton<String>("replyBtn", new Model<String>("Reply")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				replyForm.setVisible(true);

			}
		};

		add(replyForm);
		add(replyBtn);
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

}
