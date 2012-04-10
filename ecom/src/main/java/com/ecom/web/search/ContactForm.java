package com.ecom.web.search;

import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.IModel;

public class ContactForm<Message> extends StatelessForm<Message> {

	private static final long serialVersionUID = 1L;

	public ContactForm(String id, IModel<Message> model) {
		super(id, model);
	}



}
