package com.ecom.web.search;

import java.io.IOException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.ecom.web.components.gmap.GMap2;
import com.ecom.web.components.gmap.GMapHeaderContributor;
import com.ecom.web.components.gmap.api.GControl;
import com.ecom.web.components.gmap.api.GInfoWindowTab;
import com.ecom.web.components.gmap.api.GLatLng;
import com.ecom.web.components.gmap.api.GMapType;
import com.ecom.web.components.gmap.api.GMarker;
import com.ecom.web.components.gmap.api.GMarkerOptions;
import com.ecom.web.components.gmap.api.GOverlay;
import com.ecom.web.main.EcomApplication;

public class MapPanel extends Panel {

	private static final long serialVersionUID = -3261651741714233014L;
	private  FeedbackPanel feedback = null;

	public MapPanel(String id, IModel<String> addressModel) {
		super(id, addressModel);

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);

		final GMap2 bottomMap = new GMap2("bottomPanel", new GMapHeaderContributor(EcomApplication.get().getGoogleMapsAPIkey()));
		bottomMap.setOutputMarkupId(true);
		bottomMap.setMapType(GMapType.G_NORMAL_MAP);
		bottomMap.addControl(GControl.GSmallMapControl);
		add(bottomMap);
		
		if (this.getDefaultModel().getObject() != null) {

			try {
			    String address = this.getDefaultModelObjectAsString();
				GLatLng latLng = EcomApplication.get().getServerGeocoder().findAddress(address);

				bottomMap.getInfoWindow().open(latLng, new GInfoWindowTab(address, new Label(address, address)));

				GOverlay marker = new GMarker(latLng, new GMarkerOptions("My Title"));

				bottomMap.addOverlay(marker);

			} catch (IOException e) {
				error("Unable to geocode (" + e.getMessage() + ")");
			}
		}		
	}
}
