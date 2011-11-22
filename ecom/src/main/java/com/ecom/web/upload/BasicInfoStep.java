package com.ecom.web.upload;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.web.components.wizard.WizardStep;

public class BasicInfoStep extends WizardStep {

	private static final long serialVersionUID = 769908228950127137L;

	public BasicInfoStep(IModel<String> wizard_title, IModel<String> summary, ObjectId realStateId) {
        super(wizard_title, summary);

        RealState realState = new RealState();
        realState.setId(realStateId);
        CompoundPropertyModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realState);
        StatelessForm<RealState> realStateUploadInfoForm = new StatelessForm<RealState>("realStateAdvertForm", realStateModel) {

            private static final long serialVersionUID = 1L;

            @Override
            public final void onSubmit() {

            }
        };
        
        TextArea<String> title = new TextArea<String>("title");     
        TextArea<String> description = new TextArea<String>("description");
        TextArea<String> areaDescription = new TextArea<String>("areaDescription");
        TextArea<String> fittings = new TextArea<String>("fittings");       
        TextField<String> city = new TextField<String>("city");         
        TextField<String> areaCode = new TextField<String>("areaCode");     
        TextField<String> street = new TextField<String>("street");     
        TextField<String> houseNo = new TextField<String>("houseNo");       
        TextField<Double> size = new TextField<Double>("size");     
        TextField<Double> cost = new TextField<Double>("cost");     
        TextField<Double> floor = new TextField<Double>("floor");       
        TextField<Double> totalFloors = new TextField<Double>("totalFloors");       
        TextField<Double> totalRooms = new TextField<Double>("totalRooms");     
        TextField<Integer> bedRooms = new TextField<Integer>("bedRooms");       
        TextField<Integer> bathRooms = new TextField<Integer>("bathRooms");     
        CheckBox toiletWithBathRoom = new CheckBox("toiletWithBathRoom");       
        CheckBox cellarAvailable = new CheckBox("cellarAvailable");     
        CheckBox balconyAvailable = new CheckBox("balconyAvailable");       
        CheckBox liftAvailable = new CheckBox("liftAvailable");     
        CheckBox gardenAvailable = new CheckBox("gardenAvailable");     
        CheckBox heatingCostIncluded = new CheckBox("heatingCostIncluded");     
        CheckBox energyPassAvailable = new CheckBox("energyPassAvailable");     
        CheckBox kitchenAvailable = new CheckBox("kitchenAvailable");       
        CheckBox furnished = new CheckBox("furnished");     
        CheckBox animalsAllowed = new CheckBox("animalsAllowed");       
        CheckBox garageAvailable = new CheckBox("garageAvailable");     
        TextField<Double> additionalCost = new TextField<Double>("additionalCost");
        TextField<Double> depositPeriod = new TextField<Double>("depositPeriod");       
        DateTextField availableFrom = new DateTextField("availableFrom");       
        TextField<Double> garageCost = new TextField<Double>("garageCost");     
        TextField<Integer> builtYear = new TextField<Integer>("builtYear");     
        TextField<String> provisionCondition = new TextField<String>("provisionCondition");
        TextField<String> lastRenovatedYear = new TextField<String>("lastRenovatedYear");
        CheckBox provisionFree = new CheckBox("provisionFree");     
        CheckBox barrierFree = new CheckBox("barrierFree");
        CheckBox seniorAppartment = new CheckBox("seniorAppartment");
        
        realStateUploadInfoForm.add(title);
        realStateUploadInfoForm.add(description);
        realStateUploadInfoForm.add(areaDescription);
        realStateUploadInfoForm.add(fittings);
        realStateUploadInfoForm.add(city);
        realStateUploadInfoForm.add(areaCode);
        realStateUploadInfoForm.add(street);
        realStateUploadInfoForm.add(houseNo);
        realStateUploadInfoForm.add(size);
        realStateUploadInfoForm.add(cost);
        realStateUploadInfoForm.add(floor);
        realStateUploadInfoForm.add(totalFloors);
        realStateUploadInfoForm.add(totalRooms);
        realStateUploadInfoForm.add(bedRooms);
        realStateUploadInfoForm.add(bathRooms);
        realStateUploadInfoForm.add(toiletWithBathRoom);
        realStateUploadInfoForm.add(cellarAvailable);
        realStateUploadInfoForm.add(balconyAvailable);
        realStateUploadInfoForm.add(liftAvailable);
        realStateUploadInfoForm.add(gardenAvailable);
        realStateUploadInfoForm.add(heatingCostIncluded);
        realStateUploadInfoForm.add(energyPassAvailable);
        realStateUploadInfoForm.add(provisionCondition);
        realStateUploadInfoForm.add(kitchenAvailable);
        realStateUploadInfoForm.add(furnished);
        realStateUploadInfoForm.add(animalsAllowed);
        realStateUploadInfoForm.add(garageAvailable);
        
        
//      private int typeId;
//
//      private int condition;
//
//      private int categoryId;
//
//      private int heatingTypeId;

        
        realStateUploadInfoForm.add(additionalCost);
        realStateUploadInfoForm.add(depositPeriod);
        realStateUploadInfoForm.add(availableFrom);
        realStateUploadInfoForm.add(garageCost);
        realStateUploadInfoForm.add(builtYear);
        realStateUploadInfoForm.add(lastRenovatedYear);
        realStateUploadInfoForm.add(provisionFree);
        realStateUploadInfoForm.add(barrierFree);
        realStateUploadInfoForm.add(seniorAppartment);
        add(realStateUploadInfoForm);        
    }

}
