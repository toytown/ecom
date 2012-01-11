package com.ecom.web.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateRepository;
import com.ecom.service.interfaces.ImageService;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.data.RealStateDataProvider;
import com.ecom.web.main.EcomSession;
import com.ecom.web.upload.AddRealStateInfoPage;

public class EntriesPage extends UserDashBoardPage {

	private static final long serialVersionUID = -999171714434875305L;

	@SpringBean
	private RealStateRepository realStateRepository;
	
	@SpringBean
	private ImageService imageService;
	
	private String filterStr = "";
	
	public EntriesPage() {

		EcomSession session = (EcomSession) EcomSession.get();
		final String userId = session.getUserId();
		
		final TextField<String> filter = new TextField<String>("filter", new PropertyModel<String>(this, "filterStr"));		
		
		final Form<Void> filteredResultForm = new Form<Void>("filteredResultForm");
		add(filteredResultForm);
		filteredResultForm.setOutputMarkupId(true);
		
		filteredResultForm.add(filter);
		filteredResultForm.add(new AjaxButton("filterResults") {
		    
			private static final long serialVersionUID = 1L;

				@Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                String filterStrVal = filter.getDefaultModelObjectAsString();
                final ISortableDataProvider<RealState> dataProvider = new RealStateDataProvider(userId, filterStrVal);     
                final DataView<RealState> results = getDataView(dataProvider);
                List<OrderByBorder> sortOrderList = getSortOrderList(dataProvider, results);
                
                for (OrderByBorder sortOrder: sortOrderList) {
                    filteredResultForm.addOrReplace(sortOrder);
                }

                final PagingNavigator pagingNavigator = new PagingNavigator("pagingNavigator", results);
                pagingNavigator.setVisible(true);
                pagingNavigator.setOutputMarkupId(true);
                filteredResultForm.addOrReplace(pagingNavigator);
                filteredResultForm.addOrReplace(results);
                
                target.add(filteredResultForm);
                
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // TODO Auto-generated method stub
                
            }
		});
		
		final ISortableDataProvider<RealState> dataProvider = new RealStateDataProvider(userId, filterStr);		
		final DataView<RealState> results = getDataView(dataProvider);
		List<OrderByBorder> sortOrderList = getSortOrderList(dataProvider, results);
		
		for (OrderByBorder sortOrder: sortOrderList) {
		    filteredResultForm.add(sortOrder);
		}

		final PagingNavigator pagingNavigator = new PagingNavigator("pagingNavigator", results);
		pagingNavigator.setVisible(true);
		pagingNavigator.setOutputMarkupId(true);

		filteredResultForm.add(results);
		filteredResultForm.add(pagingNavigator);

	}

	public List<OrderByBorder> getSortOrderList(ISortableDataProvider<RealState> dataProvider, final DataView<RealState> results) {
	    List<OrderByBorder> sortOrderList = new ArrayList<OrderByBorder>();
	    
        OrderByBorder orderByInsertedTs = new OrderByBorder("orderByInsertedTs", "insertedTs", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                results.setCurrentPage(0);
            }
        };
        
	    OrderByBorder orderByCost = new OrderByBorder("orderByCost", "cost", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                results.setCurrentPage(0);
            }
        };
	    
	    OrderByBorder orderByRoom = new OrderByBorder("orderByRooms", "totalRooms", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                results.setCurrentPage(0);
            }
        };
	    
	    OrderByBorder orderBySize = new OrderByBorder("orderBySize", "size", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                results.setCurrentPage(0);
            }
        };
        
        results.setItemsPerPage(5);
        sortOrderList.add(orderByInsertedTs);
        sortOrderList.add(orderByCost);        
        sortOrderList.add(orderByRoom);
        sortOrderList.add(orderBySize);
        
        return sortOrderList;
	}
	
	public DataView<RealState> getDataView(ISortableDataProvider<RealState> dataProvider) {
	    
	     DataView<RealState> dataView = new DataView<RealState>("userResultsView", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final Item<RealState> item) {
                final RealState realState = (RealState) item.getModelObject();

                PageParameters detailParam = new PageParameters();
                detailParam.add("appartment-id", Model.of(realState.getId().toString()));

                if (realState.getTitleThumbNailImage() != null) {
                    StaticImage img = getTitleImageFromUrl(realState);
                    item.add(img);
                }
                // item.add(new Label("title", realState.getTitle()));
                item.add(new Label("id", Model.of(realState.getId().toString())));
                item.add(new Label("price", Model.of(String.valueOf(realState.getCost()))));
                item.add(new Label("size", Model.of(String.valueOf(realState.getSize()))));
                item.add(new Label("rooms", Model.of(String.valueOf(realState.getTotalRooms()))));
                item.add(new Link<String>("delete", new ResourceModel("deleteBtn")) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick() {
                        for (RealStateImage realStateImage : realState.getImages()) {
                            imageService.deleteImage(realState, realStateImage.getId());
                        }
                        
                        realStateRepository.delete(realState);
                    }

                });
                
                item.add(new Link<String>("edit", new ResourceModel("btn_edit")) {

                   private static final long serialVersionUID = 1L;

                   @Override
                   public void onClick() {
                       setResponsePage(AddRealStateInfoPage.class);
                   }

               });
                String addressInfo = realState.getAddressInfo();

                item.add(new Label("address", Model.of(addressInfo)));
                
                IModel<Date> insertedTs = new Model<Date>(realState.getInsertedTs());
                item.add(new DateLabel("insertedTs", insertedTs, new PatternDateConverter("yyyy-MM-dd hh:mm:sss", true)));
                
                item.add(AttributeModifier.replace("class", new AbstractReadOnlyModel<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject() {
                        return (item.getIndex() % 2 == 1) ? "even" : "odd";
                    }
                }));

            }

        };

        dataView.setOutputMarkupId(true);
        return dataView;
	}
	
    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }


}
