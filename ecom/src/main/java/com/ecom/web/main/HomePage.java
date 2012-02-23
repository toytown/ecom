package com.ecom.web.main;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.OfferType;
import com.ecom.domain.RealStateType;
import com.ecom.domain.SearchRequest;
import com.ecom.web.search.SearchResultPage;

public class HomePage extends GenericTemplatePage {

	private static final long serialVersionUID = 6571024929658974042L;
    private static final List<OfferType> offerTypeList = Arrays.asList(OfferType.Rent, OfferType.Buy);
    private static final List<RealStateType> realStateObjectList = Arrays.asList(RealStateType.Appartment, RealStateType.House, RealStateType.FurnishedAppartment, RealStateType.Land, RealStateType.Garage);
    
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

		searchForm.add(cityTxt);
		searchForm.add(areaTxt);
		searchForm.add(priceTxt);
		searchForm.add(roomsFromTxt);
		
		//typeId - Rent or Buy
        final IModel<OfferType> offerTypeModel = Model.of(req.getTypeId());
        RadioChoice<OfferType> offerType = new RadioChoice<OfferType>("typeId", offerTypeModel, offerTypeList);
        searchForm.add(offerType);
        
        //Realstate type - appartment, house, garage
        final IModel<RealStateType> realStateTypeSel = Model.of(req.getRealStateType());
       // EnumChoiceRenderer<RealStateType> realStateTypeEnum = new EnumChoiceRenderer<RealStateType>(this);
        
        DropDownChoice<RealStateType> realStateTypeDropdown = new DropDownChoice<RealStateType>("realStateType", realStateTypeSel, realStateObjectList);
        searchForm.add(realStateTypeDropdown);
        
		searchForm.add(new Button("submitSearch") {

			private static final long serialVersionUID = -8016115162670393962L;

			@Override
			public void onSubmit() {

				SearchRequest req = (SearchRequest) searchForm.getDefaultModel().getObject();

				PageParameters params = new PageParameters();
				params.set("city", req.getCity());
				params.set("areaFrom", req.getAreaFrom());
				params.set("priceTo", req.getPriceTo());
				params.set("roomsFrom", req.getRoomsFrom());
				params.set("typeId", req.getTypeId().toString());
				params.set("realStateType", req.getRealStateType().toString());
				
				setResponsePage(SearchResultPage.class, params);

			}

		});

		add(searchForm);
		add(new NewsletterPanel("newsLetter"));

	}

}
