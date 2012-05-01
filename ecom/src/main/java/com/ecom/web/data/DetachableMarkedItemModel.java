package com.ecom.web.data;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.MarkedItem;
import com.ecom.repository.MarkedItemRepository;

public class DetachableMarkedItemModel extends LoadableDetachableModel<MarkedItem> {

	private static final long serialVersionUID = 4395192389376069406L;

	@SpringBean
	private MarkedItemRepository markedItemRepository;
	
	private ObjectId id;
	
	public DetachableMarkedItemModel(ObjectId id) {
		super();
		Injector.get().inject(this);
		this.id = id;
	}

	@Override
	protected MarkedItem load() {
		return markedItemRepository.findOne(id);
	}

}
