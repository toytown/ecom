package com.ecom.web.data;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.bson.types.ObjectId;

import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateImageRepository;

public class DetachableRealStateImageModel extends LoadableDetachableModel<RealStateImage>{

	private static final long serialVersionUID = 1L;

	private String realStateImageId;
	
	private RealStateImageRepository imageRepository;
	
	public DetachableRealStateImageModel(String id) {
		super();
		this.realStateImageId = id;
		Injector.get().inject(this);
	}


	@Override
	protected RealStateImage load() {
		return imageRepository.findOne(new ObjectId(realStateImageId));
	}

}
