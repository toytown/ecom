package com.ecom.web.data;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;

import com.ecom.domain.SearchRequest;
import com.ecom.repository.SearchRequestRepository;

public class DetachableSearchRequestModel extends LoadableDetachableModel<SearchRequest> {

    @SpringBean
    private SearchRequestRepository searchRequestRepository;
    
    private ObjectId id = null;
    
    public DetachableSearchRequestModel(ObjectId id) {
        this.id = id;
        Injector.get().inject(this);
    }
    
    @Override
    protected SearchRequest load() {
        return searchRequestRepository.findOne(id);
    }

}
