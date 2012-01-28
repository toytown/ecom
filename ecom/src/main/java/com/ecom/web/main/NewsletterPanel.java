package com.ecom.web.main;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.Subscriber;
import com.ecom.repository.SubscriberRepository;

public class NewsletterPanel extends Panel {

	private static final long serialVersionUID = 4815082761207832112L;
	
	@SpringBean
	private SubscriberRepository subscriberRepository;
	
	
	public NewsletterPanel(String id) {
		super(id);
		
		final EmailTextField emailTxt = new EmailTextField("email");
		emailTxt.setRequired(true);
		
		IModel<Subscriber> subscriberModel = new CompoundPropertyModel<Subscriber>(new Subscriber());
		StatelessForm<Subscriber> newsLetterForm = new StatelessForm<Subscriber>("newsletterForm", subscriberModel) {

			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				Subscriber subscriber = this.getModelObject();
				subscriberRepository.save(subscriber);
				emailTxt.clearInput();
				clearInput();
			}
		};
		newsLetterForm.add(emailTxt);
		newsLetterForm.add(new Button("join"));
		add(newsLetterForm);
	}


}
