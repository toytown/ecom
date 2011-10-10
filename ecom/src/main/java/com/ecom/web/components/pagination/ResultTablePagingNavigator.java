package com.ecom.web.components.pagination;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

/**
 * Navigation panel for ResultTable
 *
 * @param <T> type of object displayed in ResultTable
 */
public class ResultTablePagingNavigator<T> extends Panel {

    private static final long serialVersionUID = -7836877009226971862L;

    public static final String NAVIGATION_ID = "navigation";
    
    private IDataProvider<T> dataProvider;
    private int itemsPerPage;
    private PagingNavigation pagingNavigation;
    private final IPageable pageable;
    private WebMarkupContainer backwardsLinksWrapper;
    private WebMarkupContainer forwardLinksWrapper;
    
    public ResultTablePagingNavigator(String id, IPageable pageable, IDataProvider<T> dataProvider, int itemsPerPage) {
        super(id);
        this.pageable = pageable;
        this.dataProvider = dataProvider;
        this.itemsPerPage = itemsPerPage;
        
        backwardsLinksWrapper = new WebMarkupContainer("backwardsLinksWrapper");
        add(backwardsLinksWrapper.setVisible(pageable.getCurrentPage() != 0));        
        backwardsLinksWrapper.add(new PagingNavigationLink<T>("first", pageable, 0));
        backwardsLinksWrapper.add(new PagingNavigationIncrementLink<T>("prev", pageable, -1));
        
        forwardLinksWrapper = new WebMarkupContainer("forwardLinksWrapper");
        add(forwardLinksWrapper.setVisible(pageable.getCurrentPage() != pageable.getPageCount()-1 && pageable.getPageCount() > 0));
        forwardLinksWrapper.add(new PagingNavigationIncrementLink<T>("next", pageable, 1));
        forwardLinksWrapper.add(new PagingNavigationLink<T>("last", pageable, -1));
    }

    @Override
    protected void onBeforeRender() {
        pagingNavigation = newNavigation(pageable, null);
        addOrReplace(pagingNavigation);
        
        backwardsLinksWrapper.setVisible(pageable.getCurrentPage() != 0);
        forwardLinksWrapper.setVisible(pageable.getCurrentPage() != pageable.getPageCount()-1 && pageable.getPageCount() > 0);
        
        addOrReplace(new HeadLine<T>("headline", pageable.getCurrentPage(), itemsPerPage, dataProvider.size()));
        
        super.onBeforeRender();
    }

    protected PagingNavigation newNavigation(IPageable pageable, IPagingLabelProvider labelProvider) {
        return new PagingNavigation(NAVIGATION_ID, pageable, labelProvider) {
            private static final long serialVersionUID = 329393847372033905L;

            @Override
            protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageIndex) {
                AbstractLink link = super.newPagingNavigationLink(id, pageable, pageIndex);
                if (pageIndex == pageable.getCurrentPage()) {
                    link.add(new AttributeModifier("style", new Model<String>("border: 1px solid #1D3770;")));
                }
                return link;
            }
        };
    }

    public IPageable getPageable() {
        return pageable;
    }

    private static class HeadLine<T> extends WebComponent {

        private static final long serialVersionUID = -5221401087510963928L;
        private int currentPage;
        private int itemsPerPage;
        private int itemsCount;

        public HeadLine(String id, int currentPage, int itemsPerPage, int itemsCount) {
            super(id);
            this.currentPage = currentPage;
            this.itemsPerPage = itemsPerPage;
            this.itemsCount = itemsCount;
        }

        public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
            String text = getHeadlineText();
            replaceComponentTagBody(markupStream, openTag, text);
        }

        // Custom text providing more information about the items
        public String getHeadlineText() {
            int firstListItem = currentPage * itemsPerPage;

            if (itemsCount == 0) {
                return new StringResourceModel("msg_noResultsFound", this, null).getString();
            } else {
                int endValue = firstListItem + Math.min(itemsPerPage, itemsCount);
                if (endValue > itemsCount) {
                    endValue = itemsCount;
                }
                return new StringResourceModel("msg_showingResults", this, null, new Object[]{firstListItem + 1, endValue, itemsCount}).getString();
            }
        }
    }
}
