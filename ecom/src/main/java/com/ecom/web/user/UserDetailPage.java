package com.ecom.web.user;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.RealState;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.components.pagination.CustomizedPagingNavigator;
import com.ecom.web.data.RealStateDataProvider;
import com.ecom.web.main.EcomSession;
import com.ecom.web.main.GenericTemplatePage;

public class UserDetailPage extends GenericTemplatePage {

	private static final long serialVersionUID = -999171714434875305L;
	
	public UserDetailPage() {
		
		EcomSession session = (EcomSession) EcomSession.get();
		String userName = session.getUserName();

		final ISortableDataProvider<RealState> dataProvider = new RealStateDataProvider(userName);
		
		final DataView<RealState> dataView = new DataView<RealState>("userResultsView", dataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<RealState> item) {
				final RealState realState = (RealState) item.getModelObject();

				PageParameters detailParam = new PageParameters();
				detailParam.add("appartment-id", realState.getId().toString());

				if (realState.getTitleThumbNailImage() != null) {
					StaticImage img = getTitleImageFromUrl(realState);
					item.add(img);					
				}
				item.add(new Label("title", realState.getTitle()));
				//item.add(new Label("description", realState.getDescription()));
				item.add(new Label("price", String.valueOf(realState.getCost())));
				item.add(new Label("size", String.valueOf(realState.getSize())));
				item.add(new Label("rooms", String.valueOf(realState.getTotalRooms())));

				String addressInfo = realState.getAddressInfo();

				item.add(new Label("address", addressInfo));
				
				item.add(AttributeModifier.replace("class", new AbstractReadOnlyModel<String>()
				{
					private static final long serialVersionUID = 1L;

					@Override
					public String getObject()
					{
						return (item.getIndex() % 2 == 1) ? "even" : "odd";
					}
				}));				

			}

		};
	
		dataView.setOutputMarkupId(true);
		
		add(new OrderByBorder("orderByRooms", "totalRooms", dataProvider)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged()
			{
				dataView.setCurrentPage(0);
			}
		});

		add(new OrderByBorder("orderByCost", "cost", dataProvider)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged()
			{
				dataView.setCurrentPage(0);
			}
		});
		
		add(new OrderByBorder("orderByArea", "area", dataProvider)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged()
			{
				dataView.setCurrentPage(0);
			}
		});
		
		final CustomizedPagingNavigator pagingNavigator = new CustomizedPagingNavigator("pagingNavigator", dataView, UserDetailPage.class, null);
		pagingNavigator.setVisible(true);
		pagingNavigator.setOutputMarkupId(true);
		
		add(dataView);
		add(pagingNavigator);


	}

}
