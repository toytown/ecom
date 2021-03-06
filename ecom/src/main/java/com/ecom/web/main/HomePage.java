package com.ecom.web.main;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.GeoLocation;
import com.ecom.domain.OfferType;
import com.ecom.domain.RealStateCategory;
import com.ecom.domain.SearchRequest;
import com.ecom.service.interfaces.GeoLocationService;
import com.ecom.web.search.NoResultsFoundPage;
import com.ecom.web.search.SearchResultPage;

public class HomePage extends GenericTemplatePage {

    private static final long serialVersionUID = 6571024929658974042L;
    private static final List<OfferType> offerTypeList = Arrays.asList(OfferType.Rent, OfferType.Buy);
    private static final List<RealStateCategory> realStateObjectList = Arrays.asList(RealStateCategory.Appartment, RealStateCategory.House,
            RealStateCategory.FurnishedAppartment, RealStateCategory.Land, RealStateCategory.Garage);

    @SuppressWarnings("serial")
    public static final MetaDataKey<SearchRequest> SEARCH_REQ = new MetaDataKey<SearchRequest>() {};
    
    public HomePage() {
        super();

        SearchRequest req = getSession().getMetaData(SEARCH_REQ);
        
        if (req == null ) {
            req = new SearchRequest();
            getSession().bind();
            getSession().setMetaData(SEARCH_REQ, req);
        } 
        
        CompoundPropertyModel<SearchRequest> searchReqModel = new CompoundPropertyModel<SearchRequest>(req);

        final StatelessForm<SearchRequest> searchForm = new StatelessForm<SearchRequest>("searchForm", searchReqModel);
        setStatelessHint(true);
        
        IModel<String> locationModel = searchReqModel.bind("location");
        TextField<String> cityTxt = new TextField<String>("cityOrZip", locationModel);
        //cityTxt.setRequired(true);
        
        TextField<String> areaTxt = new TextField<String>("areaFrom");
        TextField<Double> priceTxt = new TextField<Double>("priceTo");
        TextField<Double> roomsFromTxt = new TextField<Double>("roomsFrom");

        searchForm.add(cityTxt);
        searchForm.add(areaTxt);
        searchForm.add(priceTxt);
        searchForm.add(roomsFromTxt);

        // typeId - Rent or Buy
        PropertyModel<OfferType> offer = new PropertyModel<OfferType>(req, "typeId");
        RadioChoice<OfferType> offerType = new RadioChoice<OfferType>("typeId", offer, offerTypeList);
        searchForm.add(offerType);

        // Realstate type - appartment, house, garage
        // EnumChoiceRenderer<RealStateType> realStateTypeEnum = new
        // EnumChoiceRenderer<RealStateType>(this);
        DropDownChoice<RealStateCategory> realStateTypeDropdown = new DropDownChoice<RealStateCategory>("realStateType", new PropertyModel<RealStateCategory>(
                req, "realStateType"), realStateObjectList);
        searchForm.add(realStateTypeDropdown);

        searchForm.add(new Button("submitSearch") {

            private static final long serialVersionUID = -8016115162670393962L;

            @SpringBean
            private GeoLocationService geoLocationService;

            @Override
            public void onSubmit() {

                SearchRequest req = (SearchRequest) searchForm.getDefaultModel().getObject();
                PageParameters params = new PageParameters();
                if (req.getLocation() != null) {
                    GeoLocation geoLoc = geoLocationService.findLocation(req.getLocation());
                    if (geoLoc != null) {
                        params.set("loc", geoLocationService.findLocation(req.getLocation()).getId());
                    } else {
                        setResponsePage(NoResultsFoundPage.class);
                    }
                } else {
                    params.set("loc", req.getLocation());
                }
                params.set("areaFrom", req.getAreaFrom());
                params.set("priceTo", req.getPriceTo());
                params.set("roomsFrom", req.getRoomsFrom());
                params.set("typeId", OfferType.getId(req.getTypeId()));
                params.set("realStateType", RealStateCategory.getId(req.getRealStateType()));

                setResponsePage(SearchResultPage.class, params);

            }

        });

        add(searchForm);
        add(new NewsletterPanel("newsLetter"));

    }

}
