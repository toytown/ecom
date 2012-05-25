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
import com.ecom.domain.RealStateCategory;
import com.ecom.web.components.wizard.WizardStep;

public class SelectOfferStep extends WizardStep {

    private static final long serialVersionUID = 1L;
    private WebMarkupContainer realStateTypesContainer;

    private static final List<RealStateCategory> realStateRentObjectList = Arrays.asList(RealStateCategory.Appartment,
            RealStateCategory.FurnishedAppartment, RealStateCategory.House, RealStateCategory.Land, RealStateCategory.Garage);
    private static final List<RealStateCategory> realStateBuyObjectList = Arrays.asList(RealStateCategory.Appartment, RealStateCategory.House,
            RealStateCategory.Land, RealStateCategory.Garage);

    public SelectOfferStep(IModel<String> title, IModel<String> summary, final IModel<RealState> realStateModel) {
        super(title, summary);

        realStateTypesContainer = new WebMarkupContainer("realStateTypesContainer");
        realStateTypesContainer.setOutputMarkupId(true);
        realStateTypesContainer.setOutputMarkupPlaceholderTag(true);

        final Form<Void> offerSelectionForm = new Form<Void>("offerSelectionForm");
        offerSelectionForm.setOutputMarkupId(true);
        offerSelectionForm.setDefaultModel(realStateModel);

        List<OfferType> offerTypeList = Arrays.asList(OfferType.Rent, OfferType.Buy);

        final IModel<OfferType> offerTypeModel = Model.of(realStateModel.getObject().getOfferType());
        RadioChoice<OfferType> offerType = new RadioChoice<OfferType>("offerType", offerTypeModel, offerTypeList);

        offerType.add(new AjaxFormChoiceComponentUpdatingBehavior() {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {

                if (getDefaultModel() != null) {

                    OfferType offerTypeSelected = offerTypeModel.getObject();

                    RealState realState = realStateModel.getObject();
                    realState.setOfferType(offerTypeSelected);
                    realStateModel.setObject(realState);

                    List<RealStateCategory> realStateTypeList = null;

                    if (offerTypeSelected.equals(OfferType.Rent)) {
                        realStateTypeList = realStateRentObjectList;
                    } else {
                        realStateTypeList = realStateBuyObjectList;
                    }

                    final IModel<RealStateCategory> realStateCatSel = Model.of(realState.getRealStateCategory());
                    RadioChoice<RealStateCategory> realStateType = new RadioChoice<RealStateCategory>("realStateType", realStateCatSel,
                            realStateTypeList);
                    realStateTypesContainer.addOrReplace(realStateType);
                    realStateTypesContainer.setVisible(true);
                    realStateType.setRequired(true);

                    realStateType.add(new AjaxFormChoiceComponentUpdatingBehavior() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            RealStateCategory realStateCat = realStateCatSel.getObject();
                            RealState realState = realStateModel.getObject();
                            realState.setRealStateCategory(realStateCat);
                            realStateModel.setObject(realState);
                        }
                    });	   
                    
                    target.add(realStateTypesContainer);
                }
            }

        });

        offerType.setRequired(true);

        List<RealStateCategory> realStateTypeList = realStateRentObjectList;

        RealState realState = realStateModel.getObject();
        RealStateCategory realStateCategory = realState.getRealStateCategory();

        final IModel<RealStateCategory> realStateCatSel = Model.of(realStateCategory);

        RadioChoice<RealStateCategory> realStateCategoryChoice = new RadioChoice<RealStateCategory>("realStateType", realStateCatSel,
                realStateTypeList);
        realStateCategoryChoice.setRequired(true);

        realStateTypesContainer.add(realStateCategoryChoice);
        OfferType offerTypeSelected = offerTypeModel.getObject();
        realStateTypesContainer.setVisible(offerTypeSelected != null);

        offerSelectionForm.add(offerType);
        offerSelectionForm.add(realStateTypesContainer);
        add(offerSelectionForm);
    }

}
