package com.ecom.web.upload;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.ecom.domain.OfferType;
import com.ecom.domain.RealState;
import com.ecom.domain.RealStateType;
import com.ecom.web.components.wizard.WizardStep;

public class SelectOfferStep extends WizardStep {

	private static final long serialVersionUID = 1L;
	private WebMarkupContainer realStateTypesContainer;
	private static final List<RealStateType> realStateRentObjectList = Arrays.asList(RealStateType.Appartment, RealStateType.FurnishedAppartment, RealStateType.House, RealStateType.Land, RealStateType.Garage);
	private static final List<RealStateType> realStateBuyObjectList = Arrays.asList(RealStateType.Appartment, RealStateType.House, RealStateType.Land, RealStateType.Garage);
	
	public SelectOfferStep(IModel<String> title, IModel<String> summary, final IModel<RealState> realStateModel) {
		super(title, summary);


		realStateTypesContainer = new WebMarkupContainer("realStateTypesContainer");
		realStateTypesContainer.setOutputMarkupId(true);
		realStateTypesContainer.setOutputMarkupPlaceholderTag(true);

		Form<Void> offerSelectionForm = new Form<Void>("offerSelectionForm");
		offerSelectionForm.setOutputMarkupId(true);
		offerSelectionForm.setDefaultModel(realStateModel);

		
		List<OfferType> offerTypeList = Arrays.asList(OfferType.Rent, OfferType.Buy);
		
		final IModel<OfferType> offerTypeModel = Model.of(OfferType.valueOf(realStateModel.getObject().getTypeId()));
		RadioChoice<OfferType> offerType = new RadioChoice<OfferType>("typeId", offerTypeModel, offerTypeList);

		offerType.add(new AjaxFormChoiceComponentUpdatingBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			    
			    if (getDefaultModel() != null) {
			        
			        OfferType offerTypeSelected = offerTypeModel.getObject();
			        
			        RealState realState = realStateModel.getObject();			        
			        realState.setTypeId(OfferType.getId(offerTypeSelected));
			        realStateModel.setObject(realState);
			        
			        List<RealStateType> realStateTypeList = null;
			        
			        if (offerTypeSelected.equals(OfferType.Rent)) {
			            realStateTypeList = realStateRentObjectList;
			        } else {
			            realStateTypeList = realStateBuyObjectList;
			        }
			        
			        final IModel<RealStateType> realStateTypeSel = Model.of(RealStateType.valueOf(realState.getRealStateType()));
			        RadioChoice<RealStateType> realStateType = new RadioChoice<RealStateType>("realStateType", realStateTypeSel, realStateTypeList);
			        realStateTypesContainer.addOrReplace(realStateType);
			        realStateTypesContainer.setVisible(true);
			        realStateType.setRequired(true);		        

			        realStateType.add(new AjaxFormChoiceComponentUpdatingBehavior() {

						private static final long serialVersionUID = 1L;

							@Override
			            protected void onUpdate(AjaxRequestTarget target) {
			                RealStateType realStateType = realStateTypeSel.getObject();
			                
			                RealState realState = realStateModel.getObject();                   
			                realState.setRealStateType(RealStateType.getId(realStateType));
			                realStateModel.setObject(realState);
			                
			            }
			        });			        
			        target.add(realStateTypesContainer);
			    }
			}

		});
		
		offerType.setRequired(true);
		
		List<RealStateType> realStateTypeList = realStateRentObjectList;
		
		RealState realState = realStateModel.getObject();
		RealStateType realStateType = RealStateType.valueOf(realState.getRealStateType());
		
		IModel<RealStateType> realStateTypeSel = Model.of(realStateType);
		
		RadioChoice<RealStateType> realStateTypeChoice = new RadioChoice<RealStateType>("realStateType", realStateTypeSel, realStateTypeList);
		realStateTypeChoice.setRequired(true);
		
		realStateTypesContainer.add(realStateTypeChoice);
		realStateTypesContainer.setVisible(realStateType.equals(RealStateType.None));

		offerSelectionForm.add(offerType);
		offerSelectionForm.add(realStateTypesContainer);
		add(offerSelectionForm);
	}

}
