package com.ecom.web.data;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;

import com.ecom.domain.RealState;
import com.ecom.repository.RealStateRepository;

public class RealStateDataProvider extends SortableDataProvider<RealState> {

    private static final long serialVersionUID = -6508771802462213044L;
    
    @SpringBean
    private RealStateRepository realStateRepository;
    
    @Override
    public Iterator<? extends RealState> iterator(int first, int count) {
        return realStateRepository.findAll(new PageRequest(0, 20)).iterator();
    }

    public RealStateDataProvider() {
        super();
        Injector.get().inject(this);
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
