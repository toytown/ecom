package com.ecom.web.data;

import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.RealState;
import com.ecom.repository.RealStateRepository;

public class DetachableImageModel extends LoadableDetachableModel<RealState>{

	@SpringBean
	private RealStateRepository realStateRepository;

	@Override
	protected RealState load() {
		// TODO Auto-generated method stub
		return null;
	}
}
