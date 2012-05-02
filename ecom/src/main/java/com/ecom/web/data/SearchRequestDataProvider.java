package com.ecom.web.data;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ecom.domain.QSearchRequest;
import com.ecom.domain.SearchRequest;
import com.ecom.repository.SearchRequestRepository;

public class SearchRequestDataProvider extends SortableDataProvider<SearchRequest>{

    public static final int PAGE_SIZE = 15;

    public static final Sort DEFAULT_SORT = new Sort(Direction.DESC, "sentTs");

    private transient PageRequest req = new PageRequest(0, PAGE_SIZE, DEFAULT_SORT);

    
    private ObjectId userObjId = null;
    
    @SpringBean
    private SearchRequestRepository searchReqRepository;
    
    public SearchRequestDataProvider(String userId) {
        super();
        Injector.get().inject(this);
        userObjId = new ObjectId(userId);
    }

    
    @Override
    public Iterator<? extends SearchRequest> iterator(int first, int count) {
        Iterator<SearchRequest> iter = null;

        if (userObjId != null) {
            QSearchRequest searchReq = new QSearchRequest("searchRequest");

            req = new PageRequest((first + 1) / PAGE_SIZE, count, DEFAULT_SORT);
            iter = searchReqRepository.findAll(searchReq.userId.eq(userObjId), req).iterator();

        }

        return iter;
    }

    @Override
    public int size() {
        QSearchRequest searchReq = new QSearchRequest("searchRequest");
        return (int) searchReqRepository.count(searchReq.userId.eq(userObjId));
    }

    @Override
    public IModel<SearchRequest> model(SearchRequest searchReq) {
        return new DetachableSearchRequestModel(searchReq.getId());
    }

}
