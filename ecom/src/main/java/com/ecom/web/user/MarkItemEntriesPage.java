package com.ecom.web.user;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.MarkedItem;
import com.ecom.domain.RealState;
import com.ecom.service.interfaces.RealStateService;
import com.ecom.web.components.buttons.CustomButton;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.data.DetachableMarkedItemModel;
import com.ecom.web.data.MarkedItemDataProvider;
import com.ecom.web.login.LoginPage;
import com.ecom.web.main.EcomSession;

public class MarkItemEntriesPage extends UserDashBoardPage {

	private static final long serialVersionUID = 1L;
	private Set<String> selectedIds = new HashSet<String>();

	@SpringBean
	private RealStateService<RealState> realStateService;

	public MarkItemEntriesPage() {
		EcomSession session = (EcomSession) EcomSession.get();

		if (!session.isSignedIn()) {
			throw new RestartResponseAtInterceptPageException(LoginPage.class);
		}

		final String userId = session.getUserId();

		final Form<Void> filteredResultForm = new Form<Void>("markedItemsForm");
		add(filteredResultForm);
		filteredResultForm.setOutputMarkupId(true);

		final CheckGroup<String> checkgroup = new CheckGroup<String>("group", selectedIds);
		checkgroup.add(new CheckGroupSelector("groupselector"));
		filteredResultForm.add(checkgroup);

		CustomButton deleteBtn = new CustomButton("delete", new ResourceModel("btn_delete")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				for (String id : selectedIds) {
					DetachableMarkedItemModel markedItemModel = new DetachableMarkedItemModel(new ObjectId(id));
					realStateService.deleteMarkedItem(markedItemModel.getObject());
				}
			}
		};

		filteredResultForm.add(deleteBtn);

		final ISortableDataProvider<MarkedItem> dataProvider = new MarkedItemDataProvider(userId);
		final DataView<MarkedItem> results = getDataView(dataProvider);

		final PagingNavigator pagingNavigator = new PagingNavigator("pagingNavigator", results);
		pagingNavigator.setVisible(true);
		pagingNavigator.setOutputMarkupId(true);

		checkgroup.add(results);
		filteredResultForm.add(pagingNavigator);
	}

	public DataView<MarkedItem> getDataView(ISortableDataProvider<MarkedItem> dataProvider) {

		DataView<MarkedItem> dataView = new DataView<MarkedItem>("markedItemResultsView", dataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<MarkedItem> item) {
				final MarkedItem markedItem = (MarkedItem) item.getModelObject();

				item.add(new Check<String>("check", Model.of(markedItem.getId().toString())));
				item.add(new Label("id", Model.of(markedItem.getId().toString())));
				item.add(new Label("insertTs", Model.of(markedItem.getInsertTs())));
				
				RealState realState = realStateService.findOne(new ObjectId(markedItem.getItemId().toString()));

                if (realState != null && realState.getTitleThumbNailImage() != null) {
                    StaticImage img = getTitleImageFromUrl(realState);
                    item.add(img);
                    item.add(new Label("price", Model.of(String.valueOf(realState.getCost()))));
                    item.add(new Label("size", Model.of(String.valueOf(realState.getSize()))));
                    item.add(new Label("rooms", Model.of(String.valueOf(realState.getTotalRooms()))));
                    String addressInfo = realState.getAddressInfo();
                    item.add(new Label("address", Model.of(addressInfo)));
                } else{
                    Image img = getDefaultImage();
                    item.add(img);
                    item.add(new Label("price", Model.of(String.valueOf(""))));
                    item.add(new Label("size", Model.of(String.valueOf(""))));
                    item.add(new Label("rooms", Model.of(String.valueOf(""))));
                    item.add(new Label("address", Model.of("")));
                	
                }
				
			}

		};

		dataView.setOutputMarkupId(true);
		return dataView;
	}

}
