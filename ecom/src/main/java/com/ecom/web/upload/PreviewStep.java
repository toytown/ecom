package com.ecom.web.upload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateRepository;
import com.ecom.web.components.gmap.GMap2;
import com.ecom.web.components.gmap.GMapHeaderContributor;
import com.ecom.web.components.gmap.api.GControl;
import com.ecom.web.components.gmap.api.GInfoWindowTab;
import com.ecom.web.components.gmap.api.GLatLng;
import com.ecom.web.components.gmap.api.GMapType;
import com.ecom.web.components.image.EcomImageResouceReference;
import com.ecom.web.components.image.ImageNavigationPanel;
import com.ecom.web.components.image.OkCancelComponent;
import com.ecom.web.components.wizard.WizardStep;
import com.ecom.web.main.EcomApplication;
import com.ecom.web.search.ToolsPanel;

public class PreviewStep extends WizardStep {

    private static final long serialVersionUID = 1L;

    private ImageNavigationPanel imageNavigationPanel = null;

    @SpringBean
    private RealStateRepository realStateRepository;

    public PreviewStep(IModel<String> title, IModel<String> summary, final IModel<RealState> realStateModelParam) {
        super(title, summary);

        final CompoundPropertyModel<RealState> realStateModel = new CompoundPropertyModel<RealState>(realStateModelParam.getObject());
        setDefaultModel(realStateModel);

        add(new Label("contact_title", realStateModel.bind("contactInfo.title")));
        add(new Label("contact_firstName", realStateModel.bind("contactInfo.firstName")));
        add(new Label("contact_lastName", realStateModel.bind("contactInfo.lastName")));
        add(new Label("contact_email", realStateModel.bind("contactInfo.email")));
        add(new Label("contact_mobile", realStateModel.bind("contactInfo.mobile")));
        add(new Label("contact_phone", realStateModel.bind("contactInfo.phone")));

        imageNavigationPanel = new ImageNavigationPanel("imageGallery", getImageURList(realStateModelParam));
        add(imageNavigationPanel);
        add(new ToolsPanel("toolsPanel"));
        add(new MultiLineLabel("title", realStateModel.bind("title")));
        add(new Label("address", realStateModel.bind("address")));
        add(new Label("availableFrom", realStateModel.bind("availableFrom")));
        add(new MultiLineLabel("description", realStateModel.bind("description")));
        add(new MultiLineLabel("fittings", realStateModel.bind("fittings")));
        add(new MultiLineLabel("areaDescription", realStateModel.bind("areaDescription")));
        add(new MultiLineLabel("otherInformation", realStateModel.bind("otherInformation")));

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

        RealState obj = realStateModel.getObject();

        if (obj != null && obj.isKitchenAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_kitchen_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("kitchenAvailable")));
            labelId++;
        }

        if (obj != null && obj.isBalconyAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_heating_Cost")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("balconyAvailable")));
            labelId++;
        }

        if (obj != null && obj.isCellarAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_cellar_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("cellarAvailable")));
            labelId++;
        }

        if (obj != null && obj.isGarageAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_garage_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("garageAvailable")));
            labelId++;
        }

        if (obj != null && obj.isLiftAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_lift_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("liftAvailable")));
            labelId++;
        }

        if (obj != null && obj.isFurnished()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_furnished")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("furnished")));
            labelId++;
        }

        if (obj != null && obj.isToiletWithBathRoom()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_wc_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("toiletWithBathRoom")));
            labelId++;
        }

        if (obj != null && obj.isGardenAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_garden_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("gardenAvailable")));
            labelId++;
        }

        if (obj != null && obj.isAnimalsAllowed()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_animals_allowed")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("animalsAllowed")));
            labelId++;
        }

        if (obj != null && obj.isEnergyPassAvailable()) {
            add(new Label("label" + labelId, new ResourceModel("lbl_energypass_available")));
            add(new OkCancelComponent("img" + labelId, realStateModel.bind("energyPassAvailable")));
            labelId++;
        }

        if (obj != null && obj.isBarrierFree()) {
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

        add(new AjaxLazyLoadPanel("mapPanel", realStateModelParam) {

            @Override
            public Component getLazyLoadComponent(String markupId) {
                RealState realState = (RealState) this.getDefaultModel().getObject();
                final GMap2 bottomMap = new GMap2(markupId, new GMapHeaderContributor(EcomApplication.get().getGoogleMapsAPIkey()));
                bottomMap.setOutputMarkupId(true);
                bottomMap.setMapType(GMapType.G_NORMAL_MAP);
                bottomMap.addControl(GControl.GSmallMapControl);

                if (realState != null) {

                    try {
                        String address = this.getDefaultModelObjectAsString();
                        GLatLng latLng = EcomApplication.get().getServerGeocoder().findAddress(address);

                        bottomMap.getInfoWindow().open(latLng, new GInfoWindowTab(address, new Label(address, address)));

                    } catch (IOException e) {
                        error("Unable to geocode (" + e.getMessage() + ")");
                    }
                }

                return bottomMap;
            }
        }).setOutputMarkupId(true);

    }


    private IModel<List<String>> getImageURList(final IModel<RealState> realStateModel) {
        final ResourceReference imagesResourceReference = new EcomImageResouceReference();

        IModel<List<String>> realStateImagesListModel = new LoadableDetachableModel<List<String>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected List<String> load() {
                List<String> urlList = new ArrayList<String>();
                RealState realState = realStateRepository.findOne(realStateModel.getObject().getId());

                for (RealStateImage img : realState.getGalleryImages()) {
                    PageParameters imageParameters = new PageParameters();
                    String imageId = img.getId();
                    imageParameters.set("id", imageId);
                    CharSequence urlForImage = getRequestCycle().urlFor(imagesResourceReference, imageParameters);
                    urlList.add(urlForImage.toString());
                }

                return urlList;
            }
        };

        return realStateImagesListModel;
    }

}
