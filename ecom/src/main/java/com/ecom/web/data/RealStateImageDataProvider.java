package com.ecom.web.data;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ecom.domain.QRealStateImage;
import com.ecom.domain.RealStateImage;
import com.ecom.repository.RealStateImageRepository;

public class RealStateImageDataProvider extends SortableDataProvider<RealStateImage>{

	private static final long serialVersionUID = 1L;
	private String realStateId;
	
	@SpringBean
	private RealStateImageRepository realStateImageRepository;
	
	public RealStateImageDataProvider(String realStateId) {
		super();
		Injector.get().inject(this);
		this.realStateId = realStateId;
	}

	@Override
	public Iterator<? extends RealStateImage> iterator(int first, int count) {
	    QRealStateImage realStateImgQuery = new QRealStateImage("realStateImage");
		return realStateImageRepository.findAll(realStateImgQuery.realStateId.eq(this.realStateId)).iterator();
	}

	@Override
	public int size() {
		return Long.valueOf(realStateImageRepository.count()).intValue();
	}

	@Override
	public IModel<RealStateImage> model(final RealStateImage object) {
		IModel<RealStateImage> realStateModel = new LoadableDetachableModel<RealStateImage>() {

			private static final long serialVersionUID = 9180663872740807903L;

			protected RealStateImage load() {
				return realStateImageRepository.findOne(object.getId());
			}
		};

		return realStateModel;
	}

}
