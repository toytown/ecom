package com.ecom.web.upload;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.web.components.wizard.WizardStep;

public class SelectOfferStep extends WizardStep {

	private static final long serialVersionUID = 1L;
	// TODO - Internationalization
	private static final List<String> OFFER_TYPES = Arrays.asList(new String[] { "Mieten", "Kaufen" });

	private static final List<String> REAL_STATE_TYPES = Arrays.asList(new String[] { "Wohnung", "Haus", "Gewerbe" });

	private static final List<String> REAL_STATE_CATEGORY = Arrays.asList(new String[] { "Wohnung", "Haus", "Gewerbe" });

	private WebMarkupContainer realStateTypesContainer;

	private String selectedOfferType ="";
	private String selectedRealStateType ="";
	private String selectedCategoryType ="";
	
	public SelectOfferStep(IModel<String> title, IModel<String> summary, IModel<ObjectId> realStateIdModel) {
		super(title, summary);

		final RealState realState = new RealState();
		realStateTypesContainer = new WebMarkupContainer("realStateTypesContainer");
		realStateTypesContainer.setOutputMarkupId(true);
		realStateTypesContainer.setOutputMarkupPlaceholderTag(true);
		
		realState.setId(realStateIdModel.getObject());

		Form<Void> offerSelectionForm = new Form<Void>("offerSelectionForm");
		offerSelectionForm.setOutputMarkupId(true);
		IModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realState);
		offerSelectionForm.setDefaultModel(realStateModel);

		
		
		RadioChoice<String> offerType = new RadioChoice<String>("typeId", new PropertyModel<String>(this, "selectedOfferType"), OFFER_TYPES);

		offerType.add(new AjaxFormChoiceComponentUpdatingBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				realStateTypesContainer.setVisible(true);
				target.add(realStateTypesContainer);
			}

		});

		RadioChoice<String> realStateType = new RadioChoice<String>("realStateType", new PropertyModel<String>(this, "selectedRealStateType"), REAL_STATE_TYPES);
		realStateTypesContainer.add(realStateType);
		realStateTypesContainer.setVisible(false);

		offerSelectionForm.add(offerType);
		offerSelectionForm.add(realStateTypesContainer);
		add(offerSelectionForm);
	}

	public String getSelectedOfferType() {
		return selectedOfferType;
	}

	public void setSelectedOfferType(String selectedOfferType) {
		this.selectedOfferType = selectedOfferType;
	}

}
