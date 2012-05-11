package com.ecom.web.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.service.interfaces.GeoLocationService;
import com.ecom.web.main.GenericTemplatePage;

public class LocationSelectionPage extends GenericTemplatePage {

	private static final long serialVersionUID = 131512069327184887L;

	@SpringBean
	private GeoLocationService geoLocationService;

	public LocationSelectionPage(final ModalWindow modalWindow, String city) {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		IModel<List<String>> areaSelectedModel = new Model(new ArrayList<String>());
		
		List<String> locations = geoLocationService.findCityAreas(city);

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

		
	}
}
