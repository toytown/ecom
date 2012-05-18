package com.ecom.web.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.GeoLocation;
import com.ecom.service.interfaces.GeoLocationService;

public class LocationSelectionPanel extends Panel {

	private static final long serialVersionUID = 131512069327184887L;

	@SpringBean
	private GeoLocationService geoLocationService;

	public LocationSelectionPanel(String panelId, String locationCity) {

	    super(panelId);

	    this.setDefaultModel(Model.of(locationCity));
		if (locationCity == null) {		
			throw new AbortWithHttpErrorCodeException(404);
		}

	}
	
	@Override
	public void onConfigure() {
	    
	    if (getDefaultModelObject() == null) {
	        throw new AbortWithHttpErrorCodeException(404);
	    } 
	    
        @SuppressWarnings({ "unchecked", "rawtypes" })
        IModel<List<String>> areaSelectedModel = new Model(new ArrayList<String>());
        
    
        GeoLocation geoLoc = geoLocationService.findOne(getDefaultModelObjectAsString());
        List<String> locations =  geoLocationService.findCityAreas(geoLoc.getCity());
        
        CheckBoxMultipleChoice<String> areaChoice = new CheckBoxMultipleChoice<String>("areas", areaSelectedModel, locations, new IChoiceRenderer<String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public String getDisplayValue(String geoLoc) {
                return geoLoc;
            }

            @Override
            public String getIdValue(String geoLoc, int arg) {
                return geoLoc;
            }

        });

        add(areaChoice);
        this.setVisible(!locations.isEmpty());
	}
	
	@Override
	public boolean getStatelessHint() {
	    return true;
	}
}
