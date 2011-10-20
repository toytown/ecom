package com.ecom.web.data;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.repository.RealStateRepository;

public class DetachableRealStateModel extends LoadableDetachableModel<RealState> {

	private static final long serialVersionUID = 6931036907548683190L;

	@SpringBean
	private RealStateRepository realStateRepository;

	private String realStateId;

	public DetachableRealStateModel(String realStateId) {
		super();
		this.realStateId = realStateId;
		Injector.get().inject(this);
	}

	@Override
	protected RealState load() {
		return realStateRepository.findOne(new ObjectId(realStateId));
	}

}
