package com.ecom.web.components.pagination;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CustomizedPagingNavigator extends Panel {

	private static final long serialVersionUID = 8424403615326003846L;
	private PagingNavigation pagingNavigation;
	private final IPageable pageable;
	private final IPagingLabelProvider labelProvider;
	public static final String NAVIGATION_ID = "navigation";
	private int currentPage;
	private Class<? extends WebPage> clazz;
	private PageParameters params;
	public static final String PAGE_QUERY_ID = "pn";

	/** display page number starting 1, not 0 **/
	public static final int START_INDEX_POSITION = 1;
	
	public CustomizedPagingNavigator(String id, IPageable pageable, Class<? extends WebPage> clazz, PageParameters params) {
		this(id, pageable, null, clazz, params);
	}

	public CustomizedPagingNavigator(String id, IPageable pageable, IPagingLabelProvider labelProvider, Class<? extends WebPage> clazz, PageParameters params) {
		super(id);
		this.pageable = pageable;
		this.labelProvider = labelProvider;
		this.clazz = clazz;
		this.params = params;
		currentPage = pageable.getCurrentPage();

	}

	public final IPageable getPageable() {
		return pageable;
	}

	@Override
	protected void onBeforeRender() {

		// Get the navigation bar and add it to the hierarchy
		pagingNavigation = newNavigation(pageable, labelProvider);
		addOrReplace(pagingNavigation);

		// Add additional page links
		// add(newPagingNavigationLink("first", pageable, 0).add(
		// new CustomizedTitleAppender("PagingNavigator.first")));
		addOrReplace(newPagingNavigationIncrementLink("prev", pageable, -1));
		addOrReplace(newPagingNavigationIncrementLink("next", pageable, 1));
		// add(newPagingNavigationLink("last", pageable, -1).add(
		// new CustomizedTitleAppender("PagingNavigator.last")));
		super.onBeforeRender();
	}

	protected PagingNavigation newNavigation(IPageable pageable, IPagingLabelProvider labelProvider) {
		return new PagingNavigation(NAVIGATION_ID, pageable, labelProvider) {
			private static final long serialVersionUID = 329393847372033905L;

			@Override
			protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageIndex) {
				AbstractLink link = CustomizedPagingNavigator.this.newPagingNavigationLink(id, pageable, pageIndex);
				if (pageIndex == pageable.getCurrentPage()) {
					link.add(new AttributeModifier("style", new Model<String>(
							"border: 1px solid #ddd;padding: 2px 5px 2px 5px;margin-right: 2px;background-color: #d8325d;")));
				}
				return link;
			}
		};
	}

	protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageNumber) {
		PageParameters params = newPageParameters(pageable, pageNumber);
		return new BookmarkablePageLink<Void>(id, this.clazz, params);
		
//		return new PagingNavigationLink<Void>(id, pageable, pageNumber) {
//			private static final long serialVersionUID = -831137339472702847L;
//
//			@Override
//			public boolean getStatelessHint() {
//					return true;
//				
//			}
//		};
	}

	protected AbstractLink newPagingNavigationIncrementLink(String id, IPageable pageable, int increment) {
		//return new PagingNavigationIncrementLink<Void>(id, pageable, increment);
		
		PageParameters params = newPageParameters(pageable, getPageNumber(pageable, increment));
		return new BookmarkablePageLink<Void>(id, this.clazz, params);
	}
	
	@Override
	public boolean getStatelessHint() {
			return true;
		
	}	

	private int getPageNumber(IPageable pageable, int increment) {
		// Determine the page number based on the current
		// PageableListView page and the increment
		int idx = currentPage + increment;

		// make sure the index lies between 0 and the last page
		return Math.max(0, Math.min(pageable.getPageCount() - 1, idx));
	}

	private PageParameters newPageParameters(IPageable pageable, int pageNumber) {
		PageParameters params = new PageParameters(this.params);
		if (pageNumber == -1) {
			params.set(PAGE_QUERY_ID, pageable.getPageCount());
		} else {
			params.set(PAGE_QUERY_ID, pageNumber + START_INDEX_POSITION);
		}
		return params;
	}	
	

}
