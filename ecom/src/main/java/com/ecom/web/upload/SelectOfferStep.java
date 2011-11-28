package com.ecom.web.upload;

import org.apache.wicket.model.IModel;
import org.bson.types.ObjectId;

import com.ecom.web.components.wizard.WizardStep;

public class SelectOfferStep extends WizardStep {

	private static final long serialVersionUID = 1L;

	public SelectOfferStep(IModel<String> title, IModel<String> summary, IModel<ObjectId> realStateModelId) {
        super(title, summary);

    }

}
