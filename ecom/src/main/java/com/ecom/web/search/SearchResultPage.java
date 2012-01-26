package com.ecom.web.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.mapper.parameter.PageParameters.NamedPair;
import org.apache.wicket.request.resource.ResourceReference;

import com.ecom.domain.RealState;
import com.ecom.domain.RealStateSort;
import com.ecom.domain.SearchRequest;
import com.ecom.web.components.buttons.MiniButton;
import com.ecom.web.components.image.EcomImageResouceReference;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.components.pagination.CustomizedPagingNavigator;
import com.ecom.web.data.RealStateDataProvider;
import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;

public class SearchResultPage extends GenericTemplatePage {

	private static final long serialVersionUID = -6983320790900379278L;

	private PageParameters resultParams = null;


	
	public SearchResultPage(final PageParameters params) {
		this.resultParams = params;
		List<NamedPair> nameValueKey = params.getAllNamed();

		setStatelessHint(true);

		final SearchRequest req = new SearchRequest();

		for (NamedPair keyVal : nameValueKey) {

			if (keyVal.getKey().equals("city")) {
				req.setCity(keyVal.getValue());
			}

			if (keyVal.getKey().equals("areaFrom")) {
				req.setAreaFrom(Double.valueOf(keyVal.getValue()));
			}

			if (keyVal.getKey().equals("priceTo")) {
				req.setPriceTo(Double.valueOf(keyVal.getValue()));
			}

			if (keyVal.getKey().equals("roomsFrom")) {
				req.setRoomsFrom(Integer.valueOf(keyVal.getValue()));
			}

			if (keyVal.getKey().equals("roomsTo")) {
				req.setRoomsTo(Integer.valueOf(keyVal.getValue()));
			}

			if (keyVal.getKey().equals("provFree")) {
				String value = keyVal.getValue();
				if (Boolean.valueOf(value) == true)
					req.setProvisionFree(true);
			}

			if (keyVal.getKey().equals("kitchenAvail")) {
				String value = keyVal.getValue();
				if (Boolean.valueOf(value) == true)
					req.setKitchenAvailable(true);
			}

			if (keyVal.getKey().equals("furnished")) {
				String value = keyVal.getValue();
				if (Boolean.valueOf(value) == true)
					req.setFurnished(true);
			}

			if (keyVal.getKey().equals("balcon")) {
				String value = keyVal.getValue();
				if (Boolean.valueOf(value) == true)
					req.setBalconyAvailable(true);
			}

			if (keyVal.getKey().equals("liftAvail")) {
				String value = keyVal.getValue();
				if (Boolean.valueOf(value) == true)
					req.setLiftAvailable(true);
			}

			if (keyVal.getKey().equals("gardenAvail")) {
				String value = keyVal.getValue();
				if (Boolean.valueOf(value) == true)
					req.setGardenAvailable(true);
			}

		}

		CompoundPropertyModel<SearchRequest> searchReqModel = new CompoundPropertyModel<SearchRequest>(req);
		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		dataContainer.setOutputMarkupId(true);
		dataContainer.setOutputMarkupPlaceholderTag(true);

		final StatelessForm<SearchRequest> searchForm = new StatelessForm<SearchRequest>("searchForm", searchReqModel);

		IModel<RealStateSort> sortparamModel = new Model<RealStateSort>(RealStateSort.PRC_ASC);
		DropDownChoice<RealStateSort> sortResults = new DropDownChoice<RealStateSort>("sortResults", sortparamModel, Arrays.asList(RealStateSort.values()), new EnumChoiceRenderer<RealStateSort>()) {

			private static final long serialVersionUID = 1L;


			protected boolean wantOnSelectionChangedNotifications() {
            return true;
        }


        protected void onSelectionChanged(final RealStateSort newSelection) {
      	  req.setSortOption(newSelection);
        }			
		};
		searchForm.add(sortResults);
		
		TextField<Double> priceFromTxt = new TextField<Double>("priceFrom");
		TextField<Double> priceToTxt = new TextField<Double>("priceTo");
		TextField<Double> areaFromTxt = new TextField<Double>("areaFrom");
		TextField<Double> areaToTxt = new TextField<Double>("areaTo");
		TextField<Double> roomsFromTxt = new TextField<Double>("roomsFrom");
		TextField<Double> roomsToTxt = new TextField<Double>("roomsTo");
		CheckBox isProvisionFree = new CheckBox("isProvisionFree");
		CheckBox isKitchenAvailable = new CheckBox("isKitchenAvailable");
		CheckBox isFurnished = new CheckBox("isFurnished");
		CheckBox isBalconyAvailable = new CheckBox("isBalconyAvailable");
		CheckBox isLiftAvailable = new CheckBox("isLiftAvailable");
		CheckBox isGardenAvailable = new CheckBox("isGardenAvailable");

		searchForm.add(priceFromTxt);
		searchForm.add(priceToTxt);
		searchForm.add(areaFromTxt);
		searchForm.add(areaToTxt);
		searchForm.add(roomsFromTxt);
		searchForm.add(roomsToTxt);

		searchForm.add(isProvisionFree);
		searchForm.add(isKitchenAvailable);
		searchForm.add(isFurnished);
		searchForm.add(isBalconyAvailable);
		searchForm.add(isLiftAvailable);
		searchForm.add(isGardenAvailable);
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
				params.set("provFree", req.isProvisionFree());
				params.set("kitchenAvail", req.isKitchenAvailable());
				params.set("furnished", req.isFurnished());
				params.set("balcon", req.isBalconyAvailable());
				params.set("liftAvail", req.isLiftAvailable());
				params.set("gardenAvail", req.isGardenAvailable());

				setResponsePage(SearchResultPage.class, params);

			}

		});

		final ISortableDataProvider<RealState> dataProvider = new RealStateDataProvider(req);
		final EcomSession session = (EcomSession) this.getSession();

		List<String> favList = new ArrayList<String>();

		for (Entry<String, String> keyVal : session.getFavourites().entrySet()) {
			if (keyVal.getValue().length() > 36) {
				String valDisp = keyVal.getValue().substring(0, 32) + " ....";
				favList.add(valDisp);
			} else {
				favList.add(keyVal.getValue());
			}

		}

		ListView<String> markerView = new ListView<String>("view", favList) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<String> item) {
				String markedVal = item.getModelObject();
				item.add(new Label("markedRealState", Model.of(markedVal)));

			}

			@Override
			public boolean isVisible() {

				return !session.getFavourites().isEmpty();
			}

		};

		add(new MiniButton<String>("clearAll", new Model<String>("clear")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				session.clearFavourites();
			}

			@Override
			public boolean isVisible() {
				return !session.getFavourites().isEmpty();
			}
		});

		add(markerView);

		DataView<RealState> dataView = new DataView<RealState>("searchResultsView", dataProvider) {
			private static final long serialVersionUID = -8557003080882186607L;

			@Override
			protected void populateItem(Item<RealState> item) {
				final RealState realState = (RealState) item.getModelObject();

				PageParameters detailParam = new PageParameters();
				detailParam.add("appartment-id", realState.getId().toString());
				BookmarkablePageLink<String> detailImageLink = new BookmarkablePageLink<String>("detailImageLink", DetailViewPage.class, detailParam);
				BookmarkablePageLink<String> detailTitleLink = new BookmarkablePageLink<String>("detailTitleLink", DetailViewPage.class, detailParam);

				final ResourceReference imagesResourceReference = new EcomImageResouceReference();
				final PageParameters imageParameters = new PageParameters();
				String imageId = realState.getTitleThumbNailImage();
				imageParameters.set("id", imageId);

				CharSequence urlForImage = getRequestCycle().urlFor(imagesResourceReference, imageParameters);
				StaticImage img = getTitleImageFromUrl(urlForImage.toString());

				detailImageLink.add(img);
				item.add(detailImageLink);

				item.add(detailTitleLink.add(new Label("title", realState.getTitle())));
				item.add(new Label("price", String.valueOf(realState.getCost())));
				item.add(new Label("size", String.valueOf(realState.getSize())));
				item.add(new Label("rooms", String.valueOf(realState.getTotalRooms())));

				String addressInfo = realState.getAddressInfo();

				item.add(new Label("address", addressInfo));

				item.add(new MiniButton<String>("bookmark", new Model<String>("Add")) {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {

						session.addToFavourites(realState.getId().toString(), realState.getTitle());
						setResponsePage(SearchResultPage.class, params);
					}

				});

				int labelId = 1;

				if (realState.isKitchenAvailable()) {
					item.add(new Label("label" + labelId, new ResourceModel("lbl_kitchen_available")));
					labelId++;
				}

				if (realState.isBalconyAvailable()) {
					item.add(new Label("label" + labelId, new ResourceModel("lbl_balcon_available")));
					labelId++;
				}

				if (realState.isProvisionFree()) {
					item.add(new Label("label" + labelId, new ResourceModel("lbl_provision_free")));
					labelId++;
				}

				if (labelId <= 3 && realState.isToiletWithBathRoom()) {
					item.add(new Label("label" + labelId, new ResourceModel("lbl_toilet_wc")));
					labelId++;
				}

				if (labelId <= 3) {

					for (; labelId <= 3; labelId++) {
						item.add(new Label("label" + labelId, "").setVisible(false));
					}
				}
			}

		};

		int currentPage;

		// Select current page
		currentPage = getCurrentPage();
		dataView.setItemsPerPage(3);
		dataView.setCurrentPage(currentPage);

		final CustomizedPagingNavigator pagingNavigator = new CustomizedPagingNavigator("pagingNavigator", dataView, SearchResultPage.class,
				SearchResultPage.this.getPageParameters());

		dataContainer.addOrReplace(dataView);
		dataContainer.addOrReplace(pagingNavigator);
		addOrReplace(dataContainer);
		addOrReplace(searchForm);

	}

	private int getCurrentPage() {
		PageParameters params = this.resultParams;
		if (params.get(CustomizedPagingNavigator.PAGE_QUERY_ID) != null && !params.get(CustomizedPagingNavigator.PAGE_QUERY_ID).isEmpty()) {
			return params.get(CustomizedPagingNavigator.PAGE_QUERY_ID).toInt() - CustomizedPagingNavigator.START_INDEX_POSITION;
		}
		return 0;
	}

}
