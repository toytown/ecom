package com.ecom.web.user;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.domain.SearchRequest;
import com.ecom.service.interfaces.RealStateService;
import com.ecom.web.components.buttons.CustomButton;
import com.ecom.web.data.DetachableSearchRequestModel;
import com.ecom.web.data.SearchRequestDataProvider;
import com.ecom.web.login.LoginPage;
import com.ecom.web.main.EcomSession;

public class SearchEntriesPage extends UserDashBoardPage {

    private Set<String> selectedIds = new HashSet<String>();

    @SpringBean
    private RealStateService<RealState> realStateService;

    public SearchEntriesPage() {
        EcomSession session = (EcomSession) EcomSession.get();

        if (!session.isSignedIn()) {
            throw new RestartResponseAtInterceptPageException(LoginPage.class);
        }

        final String userId = session.getUserId();

        final Form<Void> filteredResultForm = new Form<Void>("searchEntriesForm");
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
                    DetachableSearchRequestModel searchReqModel = new DetachableSearchRequestModel(new ObjectId(id));
                    //realStateService.deleteMarkedItem(searchReqModel.getObject());
                }
            }
        };

        filteredResultForm.add(deleteBtn);

        final ISortableDataProvider<SearchRequest> dataProvider = new SearchRequestDataProvider(userId);
        final DataView<SearchRequest> results = getDataView(dataProvider);

        final PagingNavigator pagingNavigator = new PagingNavigator("pagingNavigator", results);
        pagingNavigator.setVisible(true);
        pagingNavigator.setOutputMarkupId(true);

        checkgroup.add(results);
        filteredResultForm.add(pagingNavigator);
    }

    public DataView<SearchRequest> getDataView(ISortableDataProvider<SearchRequest> dataProvider) {

        DataView<SearchRequest> dataView = new DataView<SearchRequest>("searchEntriesResultsView", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final Item<SearchRequest> item) {
                final SearchRequest searchReq = (SearchRequest) item.getModelObject();
                item.add(new Label("name", Model.of(searchReq.getName())));
                item.add(new Label("cityZip", Model.of(searchReq.getCityOrZip())));
                item.add(new Label("priceFrom", Model.of(searchReq.getPriceFrom())));
                item.add(new Label("priceTo", Model.of(searchReq.getPriceFrom())));
            }

        };

        dataView.setOutputMarkupId(true);
        return dataView;
    }    
}
