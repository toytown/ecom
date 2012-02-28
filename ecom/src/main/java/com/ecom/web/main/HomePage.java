package com.ecom.web.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.apache.wicket.util.string.Strings;

import com.ecom.domain.GeoLocation;
import com.ecom.domain.OfferType;
import com.ecom.domain.RealStateType;
import com.ecom.domain.SearchRequest;
import com.ecom.service.interfaces.GeoLocationService;
import com.ecom.web.components.autocomplete.JQueryAutoCompleteTextField;
import com.ecom.web.search.SearchResultPage;

public class HomePage extends GenericTemplatePage {

    private static final long serialVersionUID = 6571024929658974042L;
    private static final List<OfferType> offerTypeList = Arrays.asList(OfferType.Rent, OfferType.Buy);
    private static final List<RealStateType> realStateObjectList = Arrays.asList(RealStateType.Appartment, RealStateType.House,
            RealStateType.FurnishedAppartment, RealStateType.Land, RealStateType.Garage);

    @SpringBean
    private GeoLocationService geoLocationService;

    public HomePage() {
        super();

        SearchRequest req = new SearchRequest();
        CompoundPropertyModel<SearchRequest> searchReqModel = new CompoundPropertyModel<SearchRequest>(req);

        final StatelessForm<SearchRequest> searchForm = new StatelessForm<SearchRequest>("searchForm", searchReqModel);
        setStatelessHint(true);

        IModel<String> cityModel = searchReqModel.bind("city");
        JQueryAutoCompleteTextField<String> cityTxt = new JQueryAutoCompleteTextField<String>("city",  cityModel, "input.jqueryid_state") {
            
            @Override
            public List<?> getMatches(String term) {
                List<String> returnList = new ArrayList<String>();
                
                if (Strings.isEmpty(term)) {
                    returnList = Collections.emptyList();
                    return returnList;
                }

                Set<String> choices = new HashSet<String>(10);
                Iterator<GeoLocation> iter = geoLocationService.findByZipOrCity(term).iterator();
                while (iter.hasNext()) {
                    GeoLocation geoLoc = iter.next();

                    if (choices.size() == 10) {
                        break;
                    }

                    choices.add(geoLoc.getCity());
                }

                returnList.addAll(choices);
                
                return returnList;
            }   
        };
        
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

        DropDownChoice<RealStateType> realStateTypeDropdown = new DropDownChoice<RealStateType>("realStateType", new PropertyModel<RealStateType>(
                req, "realStateType"), realStateObjectList);
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
