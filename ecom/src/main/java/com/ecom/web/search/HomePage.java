package com.ecom.web.search;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.SearchRequest;
import com.ecom.web.main.GenericTemplatePage;

public class HomePage extends GenericTemplatePage {

	private static final long serialVersionUID = 6571024929658974042L;

	public HomePage() {
		super();

		SearchRequest req = new SearchRequest();
		CompoundPropertyModel<SearchRequest> searchReqModel = new CompoundPropertyModel<SearchRequest>(req);
		
		final StatelessForm<SearchRequest> searchForm = new StatelessForm<SearchRequest>("searchForm", searchReqModel);
		setStatelessHint(true);
		
		TextField<String> cityTxt = new TextField<String>("city");
		TextField<String> areaTxt = new TextField<String>("areaFrom");
		TextField<Double> priceTxt = new TextField<Double>("priceTo");
		TextField<Double> roomsFromTxt = new TextField<Double>("roomsFrom");
		TextField<Double> roomsToTxt = new TextField<Double>("roomsTo");

		searchForm.add(cityTxt);
		searchForm.add(areaTxt);
		searchForm.add(priceTxt);
		searchForm.add(roomsFromTxt);
		searchForm.add(roomsToTxt);
		searchForm.add(new Button("submitSearch") {

			private static final long serialVersionUID = -8016115162670393962L;

			@Override
			public void onSubmit() {

				SearchRequest req = (SearchRequest) searchForm.getDefaultModel().getObject();

				PageParameters params = new PageParameters();
				params.set("roomsFrom", req.getRoomsFrom());
				params.set("roomsTo", req.getRoomsTo());
				params.set("priceTo", req.getPriceTo());
				params.set("areaFrom", req.getAreaFrom());
				params.set("city", req.getCity());
				setResponsePage(SearchResultPage.class, params);

			}

		});

		add(searchForm);

	}

}
