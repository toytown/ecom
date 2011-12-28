package com.ecom.web.user;


import java.util.Date;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.domain.Message;
import com.ecom.web.components.pagination.CustomizedPagingNavigator;
import com.ecom.web.data.MessageDataProvider;
import com.ecom.web.main.GenericTemplatePage;

public class InboxPage extends GenericTemplatePage {

	private static final long serialVersionUID = -520591566156931738L;
	private String userId;
    
    public InboxPage(PageParameters params) {

        this.userId = params.get("userId").toString();
        
        SortableDataProvider<Message> dataProvider = new MessageDataProvider(userId);
        final DataView<Message> dataView = new DataView<Message>("userMessageResultsView", dataProvider) {

			private static final long serialVersionUID = 6097489432223932704L;

				@Override
            protected void populateItem(Item<Message> item) {
                Message msg = item.getModelObject();
                CheckBox selector = new CheckBox("messageSelector");
                
                item.add(selector);
                item.add(new Label("sender", new Model<String>(msg.getSender())));
                item.add(new Label("title", new Model<String>(msg.getTitle())));
                
                IModel<Date> dateModel = new Model<Date>(msg.getInsertedTs());       
                PatternDateConverter dateConverter = new PatternDateConverter("yyyy-mm-dd hh:mm:ss",  false);
                item.add(new DateLabel("insertedTs", dateModel, dateConverter));
                
            }
        };
        
        add(new OrderByBorder("orderBySender", "sender", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged()
            {
                dataView.setCurrentPage(0);
            }
        });
                
        add(new OrderByBorder("orderByTitle", "title", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged()
            {
                dataView.setCurrentPage(0);
            }
        }); 
        
        add(new OrderByBorder("orderByInsertTs", "insertedTs", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged()
            {
                dataView.setCurrentPage(0);
            }
        });        
        add(dataView);
        final CustomizedPagingNavigator pagingNavigator = new CustomizedPagingNavigator("pagingNavigator", dataView, InboxPage.class, params);
        pagingNavigator.setVisible(true);
        pagingNavigator.setOutputMarkupId(true);
        add(pagingNavigator);
        
    }
    
    
    
    
}
