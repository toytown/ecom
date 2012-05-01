package com.ecom.web.data;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ecom.domain.MarkedItem;
import com.ecom.domain.QMarkedItem;
import com.ecom.repository.MarkedItemRepository;

public class MarkedItemDataProvider extends SortableDataProvider<MarkedItem> {


	private static final long serialVersionUID = -2875149823760345039L;

	private String userId;
	
	public static final int PAGE_SIZE = 15;
	
	public static final Sort DEFAULT_SORT = new Sort(Direction.DESC, "insertTs");
	
	private transient PageRequest req = new PageRequest(0, PAGE_SIZE, DEFAULT_SORT);
	
	@SpringBean
	private MarkedItemRepository markedItemRepository;
	
	public MarkedItemDataProvider(String userId) {
		super();
		Injector.get().inject(this);
		this.userId = userId;
		setSort("insertTs", SortOrder.DESCENDING);
	}

	@Override
	public Iterator<? extends MarkedItem> iterator(int first, int count) {
		Iterator<MarkedItem> iter = null;

		if (!StringUtils.isEmpty(this.userId)) {
			QMarkedItem markedItemQuery = new QMarkedItem("markedItem");

			req = new PageRequest((first + 1) / PAGE_SIZE, count, DEFAULT_SORT);
			iter = markedItemRepository.findAll(markedItemQuery.userId.eq(this.userId), req).iterator();
		}

		return iter;
	}

	@Override
	public IModel<MarkedItem> model(MarkedItem markedItem) {
		return new DetachableMarkedItemModel(new ObjectId(markedItem.getId().toString()));
	}

	@Override
	public int size() {
		QMarkedItem markedItemCnt = new QMarkedItem("markedItem");		
		return Long.valueOf(markedItemRepository.count(markedItemCnt.userId.eq(this.userId))).intValue();
	}

}
