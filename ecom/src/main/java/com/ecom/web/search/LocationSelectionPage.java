package com.ecom.web.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.GeoLocation;
import com.ecom.domain.SearchRequest;
import com.ecom.service.interfaces.GeoLocationService;
import com.ecom.web.components.panel.StatelessModalWindow;
import com.ecom.web.main.GenericTemplatePage;

public class LocationSelectionPage extends GenericTemplatePage {

	private static final long serialVersionUID = 131512069327184887L;

	@SpringBean
	private GeoLocationService geoLocationService;

	public LocationSelectionPage(final StatelessModalWindow modalWindow) {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		IModel<List<String>> areaSelectedModel = new Model(new ArrayList<String>());
		
		SearchRequest req = getSession().getMetaData(SearchResultPage.SEARCH_REQ);
		GeoLocation loc = geoLocationService.findOne(req.getLocation());
		
		List<String> locations = geoLocationService.findCityAreas(loc.getCity());

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
		
	}
}
