package com.ecom.web.search;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.ecom.web.main.GenericTemplatePage;


public class HomePage extends GenericTemplatePage {

	private static final long serialVersionUID = 6571024929658974042L;

	
	public HomePage() {
		super();

		
	    SearchRequest req = new SearchRequest();
	    CompoundPropertyModel searchReqModel = new CompoundPropertyModel(req);
	    
		TextField<String> cityTxt = new TextField<String>("city", searchReqModel.bind("city"));
		TextField<String> areaTxt = new TextField<String>("area", searchReqModel.bind("area"));
		TextField<Double> priceTxt = new TextField<Double>("price", searchReqModel.bind("price"));
		TextField<Double> roomsFromTxt = new TextField<Double>("roomsFrom", searchReqModel.bind("roomsFrom"));
		TextField<Double> roomsToTxt = new TextField<Double>("roomsTo", searchReqModel.bind("roomsTo"));
        
		add(cityTxt);
		add(areaTxt);
		add(priceTxt);
		add(roomsFromTxt);
		add(roomsToTxt);
		
	}

	
}
