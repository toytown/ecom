package com.ecom.web.upload;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import com.ecom.domain.RealState;
import com.ecom.web.components.wizard.WizardStep;

public class PaymentInfoStep extends WizardStep {

	private static final long serialVersionUID = 1L;

	public PaymentInfoStep(IModel<String> title, IModel<String> summary, final IModel<RealState> realStateModel) {
		super(title, summary, realStateModel);
		
		TextField<String> referenceName = new TextField<String>("paymentInfo.referenceName");
		TextField<String> bankName = new TextField<String>("paymentInfo.bankName");
		TextField<String> blz = new TextField<String>("paymentInfo.blz");
		TextField<String> accountNumber = new TextField<String>("paymentInfo.accountNumber");
		TextArea<String> referenceText = new TextArea<String>("paymentInfo.referenceText");		
		
		add(referenceName);
		add(bankName);
		add(blz);
		add(accountNumber);
		add(referenceText);		
	}

	@Override
	public void onConfigure() {


	}
}
