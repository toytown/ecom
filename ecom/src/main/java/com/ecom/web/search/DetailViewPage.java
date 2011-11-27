package com.ecom.web.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;
import com.ecom.web.components.image.ImageNavigationPanel;
import com.ecom.web.components.image.OkCancelComponent;
import com.ecom.web.data.DetachableRealStateModel;
import com.ecom.web.main.GenericTemplatePage;

public class DetailViewPage extends GenericTemplatePage {


	private static final long serialVersionUID = 8694888856825299786L;


	
	public DetailViewPage(PageParameters params) {
		String appartmentId = params.get("appartment-id").toString();
		IModel<RealState> realState = new DetachableRealStateModel(new ObjectId(appartmentId));
		setStatelessHint(true);

		final CompoundPropertyModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realState);
		setDefaultModel(realStateModel);

		
		ImageNavigationPanel imageNavigationPanel = new ImageNavigationPanel("imageGallery", getImageURList(realState.getObject()));
		add(imageNavigationPanel);
		add(new ToolsPanel("toolsPanel"));
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
		add(new OkCancelComponent("provisionFree", realStateModel.bind("provisionFree")));
		add(new Label("provisionCondition", realStateModel.bind("provisionCondition")));
		add(new Label("lastRenovatedYear", realStateModel.bind("lastRenovatedYear")));
		add(new Label("builtYear", realStateModel.bind("builtYear")));
		add(new Label("depositPeriod", realStateModel.bind("depositPeriod")));
		
		add(new OkCancelComponent("heatingCostIncluded", realStateModel.bind("heatingCostIncluded")));

		int labelId = 1;

        if (realState.getObject().isKitchenAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_kitchen_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("kitchenAvailable")));
            labelId++;
        }
        
		if (realState.getObject().isBalconyAvailable()) {
		    add(new Label("label" + labelId, new ResourceModel("lbl_heating_Cost")));
		    add(new OkCancelComponent("img" + labelId, realStateModel.bind("balconyAvailable")));
		    labelId++;
		}
		
        if (realState.getObject().isCellarAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_cellar_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("cellarAvailable")));
            labelId++;
        }
        
        if (realState.getObject().isGarageAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_garage_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("garageAvailable")));
            labelId++;
        }
        
        if (realState.getObject().isLiftAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_lift_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("liftAvailable")));
            labelId++;
        }
        
        if (realState.getObject().isFurnished()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_furnished")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("furnished")));
            labelId++;
        }
        
        if (realState.getObject().isToiletWithBathRoom()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_wc_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("toiletWithBathRoom")));
            labelId++;
        }
        
        if (realState.getObject().isGardenAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_garden_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("gardenAvailable")));
            labelId++;
        }
        
        if (realState.getObject().isAnimalsAllowed()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_animals_allowed")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("animalsAllowed")));
            labelId++;
        }
        
        if (realState.getObject().isEnergyPassAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_energypass_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("energyPassAvailable")));
            labelId++;
        }
        
        if (realState.getObject().isBarrierFree()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_barrier_free")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("barrierFree")));
            labelId++;
        }

        if (labelId <= 5) {

            for (; labelId <= 5; labelId++) {
                add(new Label("label" + labelId, "").setVisible(false));
                add(new OkCancelComponent("img" + labelId, realStateModel.bind("barrierFree")).setVisible(false));
            }
        }        
	}
	
	private List<String> getImageURList(RealState realState) {
		List<RealStateImage> images = realState.getImages();
		List<String> urls = new ArrayList<String>();
		
		for (RealStateImage realStateImg : images) {
			urls.add(realStateImg.getImageURL());

		}
		
		return urls;
	}
}
