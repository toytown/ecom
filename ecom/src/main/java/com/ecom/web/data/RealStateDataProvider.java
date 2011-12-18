package com.ecom.web.data;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ecom.domain.QRealState;
import com.ecom.domain.RealState;
import com.ecom.repository.RealStateRepository;
import com.ecom.web.search.SearchRequest;

public class RealStateDataProvider extends SortableDataProvider<RealState> {

	public static final int PAGE_SIZE = 5;
	
	private static final long serialVersionUID = -6508771802462213044L;
	public static final Sort DEFAULT_SORT = new Sort(Direction.DESC, "insertTs");
	private transient PageRequest req = new PageRequest(0, PAGE_SIZE, DEFAULT_SORT);
	private String userName = null;
	
	@SpringBean
	private RealStateRepository realStateRepository;

	public RealStateDataProvider() {
		super();
		Injector.get().inject(this);
	}

	public RealStateDataProvider(String userName) {
		this();
		this.userName = userName;
		setSort("insertTs", SortOrder.DESCENDING);
	}

	public RealStateDataProvider(SearchRequest request) {
		this();
	}

	@Override
	public Iterator<? extends RealState> iterator(int first, int count) {


		Iterator<RealState> iter = null;

		
		if (!StringUtils.isEmpty(userName)) {
			QRealState realStateQuery = new QRealState("realState");			

			SortParam sortParam = this.getSort();
			
			if (sortParam.getProperty().equalsIgnoreCase("cost")) {
				Sort sort = new Sort(Direction.ASC, "cost" );
				req = new PageRequest((first + 1) / PAGE_SIZE, count, sort);
				iter = realStateRepository.findAll(realStateQuery.userName.eq(userName), req).iterator();
			}
			
			if (sortParam.getProperty().equalsIgnoreCase("totalRooms")) {
				Sort sort = new Sort(Direction.ASC, "totalRooms" );
				req = new PageRequest((first + 1) / PAGE_SIZE, count, sort);
				iter = realStateRepository.findAll(realStateQuery.userName.eq(userName), req).iterator();
			}
			
			if (sortParam.getProperty().equalsIgnoreCase("area")) {
				Sort sort = new Sort(Direction.ASC, "size" );
				req = new PageRequest((first + 1) / PAGE_SIZE, count, sort);
				iter = realStateRepository.findAll(realStateQuery.userName.eq(userName), req).iterator();
			}	
			
			if (sortParam.getProperty().equalsIgnoreCase("insertTs")) {
				Sort sort = new Sort(Direction.DESC, "id" );
				req = new PageRequest((first + 1) / PAGE_SIZE, count, sort);
				iter = realStateRepository.findAll(realStateQuery.userName.eq(userName), req).iterator();
			}			
			
		} else {
			iter = realStateRepository.findAll(req).iterator();
		}

		return iter;

	}

	@Override
	public int size() {
		return Long.valueOf(realStateRepository.count()).intValue();
	}

	@Override
	public IModel<RealState> model(final RealState object) {
		IModel<RealState> realStateModel = new LoadableDetachableModel<RealState>() {

			private static final long serialVersionUID = 9180663872740807903L;

			protected RealState load() {
				return realStateRepository.findOne(object.getId());
			}
		};

		return realStateModel;
	}

}
