package com.ecom.web.login;

import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.RealState;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.components.pagination.CustomizedPagingNavigator;
import com.ecom.web.data.RealStateDataProvider;
import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;
import com.ecom.web.search.DetailViewPage;

public class UserDetailPage extends GenericTemplatePage {

	private static final long serialVersionUID = -999171714434875305L;
	
	public UserDetailPage() {
		
		EcomSession session = (EcomSession) EcomSession.get();
		String userName = session.getUserName();

		final ISortableDataProvider<RealState> dataProvider = new RealStateDataProvider(userName);

		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		
		DataView<RealState> dataView = new DataView<RealState>("userResultsView", dataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<RealState> item) {
				final RealState realState = (RealState) item.getModelObject();

				PageParameters detailParam = new PageParameters();
				detailParam.add("appartment-id", realState.getId().toString());
				BookmarkablePageLink<String> detailImageLink = new BookmarkablePageLink<String>("detailImageLink", DetailViewPage.class, detailParam);
				BookmarkablePageLink<String> detailTitleLink = new BookmarkablePageLink<String>("detailTitleLink", DetailViewPage.class, detailParam);

				if (realState.getTitleImage() != null) {
					StaticImage img = getTitleImageFromUrl(realState);
					detailImageLink.add(img);
					item.add(detailImageLink);					
				} else {
					StaticImage img = getTitleImageFromUrl(realState);
					detailImageLink.add(img);
					item.add(detailImageLink);					
				}

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
	
		dataView.setOutputMarkupId(true);
		final CustomizedPagingNavigator pagingNavigator = new CustomizedPagingNavigator("pagingNavigator", dataView, UserDetailPage.class, null);
		pagingNavigator.setVisible(true);
		pagingNavigator.setOutputMarkupId(true);
		
		dataContainer.add(dataView);
		dataContainer.add(pagingNavigator);
		add(dataContainer);

	}

}
