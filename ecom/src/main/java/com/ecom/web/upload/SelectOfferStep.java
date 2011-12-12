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
import org.apache.wicket.model.Model;
import org.bson.types.ObjectId;

import com.ecom.domain.OfferType;
import com.ecom.domain.RealState;
import com.ecom.domain.RealStateType;
import com.ecom.web.components.wizard.WizardStep;

public class SelectOfferStep extends WizardStep {

	private static final long serialVersionUID = 1L;
	private WebMarkupContainer realStateTypesContainer;
	
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

		
		List<OfferType> offerTypeList = Arrays.asList(OfferType.Rent, OfferType.Buy);
		final IModel<OfferType> defaultOfferTypeModel = Model.of(OfferType.None);
		RadioChoice<OfferType> offerType = new RadioChoice<OfferType>("typeId", defaultOfferTypeModel, offerTypeList);

		offerType.add(new AjaxFormChoiceComponentUpdatingBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			    
			    if (getDefaultModel()!= null) {
			        OfferType offerTypeSelected = defaultOfferTypeModel.getObject();
			        List<RealStateType> realStateTypeList = null;
			        if (offerTypeSelected.equals(OfferType.Rent)) {
			            realStateTypeList = Arrays.asList(RealStateType.Appartment, RealStateType.FurnishedAppartment, RealStateType.House, RealStateType.Land, RealStateType.Garage);
			        } else {
			            realStateTypeList = Arrays.asList(RealStateType.Appartment, RealStateType.House, RealStateType.Land, RealStateType.Garage);
			        }
			        IModel<RealStateType> defaultRealStateType = Model.of(RealStateType.None);
			        RadioChoice<RealStateType> realStateType = new RadioChoice<RealStateType>("realStateType", defaultRealStateType, realStateTypeList);
			        realStateTypesContainer.addOrReplace(realStateType);
			        target.add(realStateTypesContainer);
			    }
			}

		});
		offerType.setRequired(true);
		List<RealStateType> realStateTypeList = Arrays.asList(RealStateType.Appartment, RealStateType.FurnishedAppartment, RealStateType.House, RealStateType.Land, RealStateType.Garage);
        IModel<RealStateType> defaultRealStateType = Model.of(RealStateType.None);
        RadioChoice<RealStateType> realStateType = new RadioChoice<RealStateType>("realStateType", defaultRealStateType, realStateTypeList);
        realStateType.setRequired(true);
        realStateTypesContainer.add(realStateType);
		realStateTypesContainer.setVisible(false);
		
		offerSelectionForm.add(offerType);
		offerSelectionForm.add(realStateTypesContainer);
		add(offerSelectionForm);
	}

}
