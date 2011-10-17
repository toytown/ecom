package com.ecom.web.components.pagination;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class CustomizedPagingNavigator extends Panel {

	private static final long serialVersionUID = 8424403615326003846L;
	private PagingNavigation pagingNavigation;
	private final IPageable pageable;
	private final IPagingLabelProvider labelProvider;
	public static final String NAVIGATION_ID = "navigation";

	public CustomizedPagingNavigator(String id, IPageable pageable) {
		this(id, pageable, null);
	}

	public CustomizedPagingNavigator(String id, IPageable pageable, IPagingLabelProvider labelProvider) {
		super(id);
		this.pageable = pageable;
		this.labelProvider = labelProvider;

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
		return new PagingNavigationLink<Void>(id, pageable, pageNumber) {
			private static final long serialVersionUID = -831137339472702847L;

			@Override
			public boolean getStatelessHint() {
					return true;
				
			}
		};
	}

	protected AbstractLink newPagingNavigationIncrementLink(String id, IPageable pageable, int increment) {
		return new PagingNavigationIncrementLink<Void>(id, pageable, increment);
	}
	
	@Override
	public boolean getStatelessHint() {
			return true;
		
	}	

}
