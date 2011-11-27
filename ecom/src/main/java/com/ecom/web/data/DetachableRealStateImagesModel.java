package com.ecom.web.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateRepository;

public class DetachableRealStateImagesModel extends LoadableDetachableModel<List<String>>{

	private static final long serialVersionUID = 1L;

	@SpringBean
	private RealStateRepository realStateRepository;
	
	private ObjectId realStateId;
	
	public DetachableRealStateImagesModel(ObjectId realStateId) {
		super();
		Injector.get().inject(this);
		this.realStateId = realStateId;
	}
	
	@Override
	protected List<String> load() {

		List<String> urlList = new ArrayList<String>();
		RealState realState = realStateRepository.findOne(realStateId);

		if (realState != null) {
			for (RealStateImage img : realState.getImages()) {
				urlList.add(img.getImageURL());
			}
			
		}
		
		if (urlList.isEmpty()) {
			urlList.add("");
		}
		return urlList;
	}

}
