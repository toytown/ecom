package com.ecom.web.search;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.RealState;
import com.ecom.web.data.DetachableRealStateModel;
import com.ecom.web.main.GenericTemplatePage;

public class DetailViewPage extends GenericTemplatePage {


	//private int categoryId;

	//private int heatingTypeId;

	//private String imageLocation;


	private static final long serialVersionUID = 8694888856825299786L;

	public DetailViewPage(PageParameters params) {
		String appartmentId = params.get("appartment-id").toString();
		IModel<RealState> realState = new DetachableRealStateModel(appartmentId);
		setStatelessHint(true);
		CompoundPropertyModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realState);
		setDefaultModel(realStateModel);
		
		
		add(new MultiLineLabel("title",  realStateModel.bind("title")));
		add(new Label("address", realStateModel.bind("addressInfo")));
		add(new Label("availableFrom", realStateModel.bind("availableFrom")));
		add(new MultiLineLabel("description",  realStateModel.bind("description")));
		add(new MultiLineLabel("fittings",  realStateModel.bind("fittings")));
		add(new MultiLineLabel("areaDescription",  realStateModel.bind("areaDescription")));
		add(new MultiLineLabel("otherInformation",  realStateModel.bind("otherInformation")));
		
		add(new Label("cost", realStateModel.bind("cost")));
		add(new Label("additionalCost", realStateModel.bind("additionalCost")));
		add(new Label("garageCost", realStateModel.bind("garageCost")));
		
		add(new Label("size", realStateModel.bind("size")));
		add(new Label("totalRooms", realStateModel.bind("totalRooms")));
		add(new Label("bedRooms", realStateModel.bind("bedRooms")));
		add(new Label("bathRooms", realStateModel.bind("bathRooms")));
		
		add(new Label("totalFloors", realStateModel.bind("totalFloors")));
		add(new Label("floor", realStateModel.bind("floor")));
		add(new Label("condition", realStateModel.bind("condition")));
		add(new Label("provisionFree", realStateModel.bind("provisionFree")));
		add(new Label("provisionCondition", realStateModel.bind("provisionCondition")));
		add(new Label("lastRenovatedYear", realStateModel.bind("lastRenovatedYear")));
		add(new Label("builtYear", realStateModel.bind("builtYear")));
		add(new Label("depositPeriod", realStateModel.bind("depositPeriod")));
		
		
		add(new Label("heatingCostIncluded", realStateModel.bind("heatingCostIncluded")));
		add(new Label("cellarAvailable", realStateModel.bind("cellarAvailable")));
		add(new Label("balconyAvailable", realStateModel.bind("balconyAvailable")));		
		add(new Label("liftAvailable", realStateModel.bind("liftAvailable")));
		add(new Label("gardenAvailable", realStateModel.bind("gardenAvailable")));
		add(new Label("toiletWithBathRoom", realStateModel.bind("toiletWithBathRoom")));
		add(new Label("kitchenAvailable", realStateModel.bind("kitchenAvailable")));
		add(new Label("energyPassAvailable", realStateModel.bind("energyPassAvailable")));
		add(new Label("animalsAllowed", realStateModel.bind("animalsAllowed")));
		add(new Label("furnished", realStateModel.bind("furnished")));
		add(new Label("garageAvailable", realStateModel.bind("garageAvailable")));
		add(new Label("barrierFree", realStateModel.bind("barrierFree")));
	}
}