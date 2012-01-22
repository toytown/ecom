package com.ecom.web.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.ecom.domain.RealState;
import com.ecom.domain.SearchRequest;
import com.ecom.service.interfaces.RealStateService;

public class RealStateDataProvider extends SortableDataProvider<RealState> {

    public static final int PAGE_SIZE = 5;

    private static final long serialVersionUID = -6508771802462213044L;
    public static final Sort DEFAULT_SORT = new Sort(Direction.DESC, "insertTs");
    private transient PageRequest pageReq = new PageRequest(0, PAGE_SIZE, DEFAULT_SORT);
    private String userId = null;
    private String filterVal = null;
    private transient SearchRequest searchRequest = null;
    
    @SpringBean
    private RealStateService<RealState> realStateService;

    
    public RealStateDataProvider() {
        super();
        Injector.get().inject(this);
    }

    public RealStateDataProvider(String userId, String filterVal) {
        this();
        this.userId = userId;
        this.filterVal = filterVal;
        setSort("insertTs", SortOrder.DESCENDING);
    }

    public RealStateDataProvider(SearchRequest request) {
        this();
        this.searchRequest = request;
    }

    @Override
    public Iterator<? extends RealState> iterator(int first, int count) {

        Iterator<RealState> iter = null;

        if (!StringUtils.isEmpty(userId)) {

            SortParam sortParam = this.getSort();
            Sort sort = new Sort(getSortOrder(sortParam));

            pageReq = new PageRequest((first + 1) / PAGE_SIZE, count, sort);

            iter = realStateService.findBySearchRequest(searchRequest, pageReq).iterator();
            return iter;

        } else {
      	  pageReq = new PageRequest((first + 1) / PAGE_SIZE, count);
            iter = realStateService.findBySearchRequest(searchRequest, pageReq).iterator();
        }

        return iter;

    }

    
 	public List<Order> getSortOrder(SortParam sortParam) {
		List<Order> sortOrder = new ArrayList<Sort.Order>();

		Order orderDefault = new Order(Direction.DESC, "insertedTs");
		Order orderSelected = null;

		if (sortParam.getProperty().equals("insertTs")) {
			orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "insertedTs");
			sortOrder.add(orderSelected);
		}

		if (sortParam.getProperty().equals("cost")) {
			orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "cost");
			sortOrder.add(orderSelected);
		}

		if (sortParam.getProperty().equals("totalRooms")) {
			orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "totalRooms");
			sortOrder.add(orderSelected);
		}

		if (sortParam.getProperty().equals("size")) {
			orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "size");
			sortOrder.add(orderSelected);
		}

		if (sortOrder.isEmpty()) {
			sortOrder.add(orderDefault);
		}

		return sortOrder;
	}

    @Override
    public int size() {
   	 
        if (!StringUtils.isEmpty(userId)) {
           return realStateService.count(userId, filterVal);
        } else {
      	  return realStateService.count(searchRequest);
        }

    }

    @Override
    public IModel<RealState> model(final RealState object) {
        return new DetachableRealStateModel(object.getId());
    }

}
