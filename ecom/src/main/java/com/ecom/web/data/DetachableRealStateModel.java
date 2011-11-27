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

	private ObjectId realStateId;

	public DetachableRealStateModel() {
		super();
		Injector.get().inject(this);
	}
	
	public DetachableRealStateModel(ObjectId realStateId) {
		this();
		this.realStateId = realStateId;
	}

	@Override
	protected RealState load() {
		if (this.realStateId == null) {
			RealState realState = new RealState();
			realState.setId(new ObjectId());
			return realState;
		}		
		
		return realStateRepository.findOne(realStateId);

	}

}
