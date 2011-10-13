package com.ecom.web.components.pagination;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

public class CustomizedPagingNavigator extends PagingNavigator {

	private static final long serialVersionUID = 8424403615326003846L;

	public CustomizedPagingNavigator(String id, IPageable pageable) {
        super(id, pageable);
    }

    public CustomizedPagingNavigator(String id, IPageable pageable, IPagingLabelProvider labelProvider) {
        super(id, pageable, labelProvider);

    }

}
