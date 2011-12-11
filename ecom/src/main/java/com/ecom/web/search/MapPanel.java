package com.ecom.web.search;

import java.io.IOException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.GMapHeaderContributor;
import wicket.contrib.gmap.api.GControl;
import wicket.contrib.gmap.api.GInfoWindowTab;
import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GMapType;
import wicket.contrib.gmap.api.GMarker;
import wicket.contrib.gmap.api.GMarkerOptions;
import wicket.contrib.gmap.api.GOverlay;

import com.ecom.web.main.EcomApplication;

public class MapPanel extends Panel {

	private static final long serialVersionUID = -3261651741714233014L;
	private final FeedbackPanel feedback;

	public MapPanel(String id, String address) {
		super(id);

		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);

		final GMap2 bottomMap = new GMap2("bottomPanel", new GMapHeaderContributor(EcomApplication.get().getGoogleMapsAPIkey()));
		bottomMap.setOutputMarkupId(true);
		bottomMap.setMapType(GMapType.G_NORMAL_MAP);
		bottomMap.addControl(GControl.GSmallMapControl);
		add(bottomMap);

		try {
			GLatLng latLng = EcomApplication.get().getServerGeocoder().findAddress(address);

			bottomMap.getInfoWindow().open(latLng, new GInfoWindowTab(address, new Label(address, address)));

			GOverlay marker = new GMarker(latLng, new GMarkerOptions("My Title"));

			bottomMap.addOverlay(marker);

			
		} catch (IOException e) {
			error("Unable to geocode (" + e.getMessage() + ")");
		}
	}

}
