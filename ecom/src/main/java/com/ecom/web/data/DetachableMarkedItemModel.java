package com.ecom.web.data;

import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.MarkedItem;
import com.ecom.repository.RealStateRepository;

public class DetachableMarkedItemModel extends LoadableDetachableModel<MarkedItem> {

	@SpringBean
	private RealStateRepository realStateRepository;
	
	
	@Override
	protected MarkedItem load() {
		// TODO Auto-generated method stub
		return null;
	}

}
