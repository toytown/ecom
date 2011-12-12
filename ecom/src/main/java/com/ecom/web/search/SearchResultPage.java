package com.ecom.web.search;

import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;

import com.ecom.domain.RealState;
import com.ecom.web.components.image.EcomImageResouceReference;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.components.pagination.CustomizedPagingNavigator;
import com.ecom.web.data.RealStateDataProvider;
import com.ecom.web.main.GenericTemplatePage;

public class SearchResultPage extends GenericTemplatePage {

	private static final long serialVersionUID = -6983320790900379278L;

	private PageParameters resultParams = null;
	public SearchResultPage(final PageParameters params) {
		this.resultParams = params;
		// List<NamedPair> nameValueKey = params.getAllNamed();
		setStatelessHint(true);
		
		SearchRequest req = new SearchRequest();
		CompoundPropertyModel<SearchRequest> searchReqModel = new CompoundPropertyModel<SearchRequest>(req);
		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		dataContainer.setOutputMarkupId(true);
		dataContainer.setOutputMarkupPlaceholderTag(true);

		StatelessForm<SearchRequest> searchForm = new StatelessForm<SearchRequest>("searchForm", searchReqModel);

		TextField<Double> priceFromTxt = new TextField<Double>("priceFrom");
		TextField<Double> priceToTxt = new TextField<Double>("priceTo");
		TextField<Double> areaFromTxt = new TextField<Double>("areaFrom");
		TextField<Double> areaToTxt = new TextField<Double>("areaTo");
		TextField<Double> roomsFromTxt = new TextField<Double>("roomsFrom");
		TextField<Double> roomsToTxt = new TextField<Double>("roomsTo");

		searchForm.add(priceFromTxt);
		searchForm.add(priceToTxt);
		searchForm.add(areaFromTxt);
		searchForm.add(areaToTxt);
		searchForm.add(roomsFromTxt);
		searchForm.add(roomsToTxt);

		final ISortableDataProvider<RealState> dataProvider = new RealStateDataProvider();

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
		
		final CustomizedPagingNavigator pagingNavigator = new CustomizedPagingNavigator("pagingNavigator", dataView, SearchResultPage.class, SearchResultPage.this.getPageParameters());


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
