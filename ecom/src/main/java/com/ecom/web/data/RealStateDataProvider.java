package com.ecom.web.data;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;

import com.ecom.domain.QRealState;
import com.ecom.domain.RealState;
import com.ecom.repository.RealStateRepository;
import com.ecom.web.search.SearchRequest;

public class RealStateDataProvider extends SortableDataProvider<RealState> {

	private static final long serialVersionUID = -6508771802462213044L;
	private PageRequest req = new PageRequest(0, 3);
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
	}

	public RealStateDataProvider(SearchRequest request) {
		this();
	}

	@Override
	public Iterator<? extends RealState> iterator(int first, int count) {

		req = new PageRequest((first + 1) / 3, count);
		Iterator<RealState> iter = null;
		if (!StringUtils.isEmpty(userName)) {
			QRealState realStateQuery = new QRealState("realState");
			iter = realStateRepository.findAll(realStateQuery.userName.eq(userName)).iterator();
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
