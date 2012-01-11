package com.ecom.web.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ecom.domain.QRealState;
import com.ecom.domain.RealState;
import com.ecom.domain.SearchRequest;
import com.ecom.repository.RealStateRepository;
import com.mysema.query.types.Predicate;

public class RealStateDataProvider extends SortableDataProvider<RealState> {

    public static final int PAGE_SIZE = 5;

    private static final long serialVersionUID = -6508771802462213044L;
    public static final Sort DEFAULT_SORT = new Sort(Direction.DESC, "insertTs");
    private transient PageRequest req = new PageRequest(0, PAGE_SIZE, DEFAULT_SORT);
    private String userId = null;
    private String filterVal = null;

    @SpringBean
    private RealStateRepository realStateRepository;

    @SpringBean
    private MongoTemplate mongoTemplate;
    
    public RealStateDataProvider() {
        super();
        Injector.get().inject(this);
    }

    public RealStateDataProvider(String userId, String filterVal) {
        this();
        this.userId = userId;
        this.filterVal = filterVal;
        setSort("insertTs", SortOrder.DESCENDING);
    }

    public RealStateDataProvider(SearchRequest request) {
        this();
    }

    @Override
    public Iterator<? extends RealState> iterator(int first, int count) {

        Iterator<RealState> iter = null;

        if (!StringUtils.isEmpty(userId)) {

            SortParam sortParam = this.getSort();
            Sort sort = new Sort(getSortOrder(sortParam));

            req = new PageRequest((first + 1) / PAGE_SIZE, count, sort);

            iter = realStateRepository.findAll(buildPredicate(userId, filterVal), req).iterator();
            return iter;

        } else {
            //iter = realStateRepository.findAll(req).iterator();
            mongoTemplate.find(getSearchQuery(), RealState.class);
        }

        return iter;

    }

    public Query getSearchQuery() {
        SearchRequest req = null;
        
        Criteria searchCriteria = new Criteria();
        
        if (req.getAreaFrom() > 0.0) {
            searchCriteria.and("size").gt(req.getAreaFrom());
        }
        
        if (req.getAreaTo() > 0.0) {
            searchCriteria.and("size").lt(req.getAreaFrom());
        }
        
        if (req.getRoomsFrom() > 0.0) {
            Criteria.where("totalRooms").gt(req.getRoomsFrom());
        }

        if (req.getRoomsTo() > 0.0) {
            searchCriteria.and("totalRooms").lt(req.getRoomsTo());
        }
        
        if (req.getPriceFrom() > 0.0) {
            searchCriteria.and("cost").gt(req.getPriceFrom());
        }

        if (req.getPriceTo() > 0.0) {
            searchCriteria.and("cost").lt(req.getPriceTo());
        }
        Query query = new Query(searchCriteria);
        return query;
    }
    public Predicate buildPredicate(SearchRequest req) {
        QRealState realStateQuery = new QRealState("realStateUser");
        Predicate condition = null;

            condition = realStateQuery.userId.eq(userId).and(
                    realStateQuery.city.contains(filterVal).or(realStateQuery.street.contains(filterVal))
                            .or(realStateQuery.id.eq(new ObjectId(filterVal))));

        return condition;
    }
    
    public Predicate buildPredicate(String userId, String filterVal) {
        QRealState realStateQuery = new QRealState("realStateUser");
        Predicate condition = null;
        if (!StringUtils.isEmpty(filterVal)) {
            condition = realStateQuery.userId.eq(userId).and(
                    realStateQuery.city.contains(filterVal).or(realStateQuery.street.contains(filterVal))
                            .or(realStateQuery.id.eq(new ObjectId(filterVal))));
        } else {
            condition = realStateQuery.userId.eq(userId);
        }

        return condition;
    }

    public List<Order> getSortOrder(SortParam sortParam) {
        List<Order> sortOrder = new ArrayList<Sort.Order>();
        
        Order orderDefault = new Order(Direction.DESC, "insertedTs");
        Order orderSelected = null; 
        
        if (sortParam.getProperty().equals("insertTs")) {
            orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "insertedTs");
            sortOrder.add(orderSelected);
        }        

        if (sortParam.getProperty().equals("cost")) {
            orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "cost");
            sortOrder.add(orderSelected);
        }
        
        if (sortParam.getProperty().equals("totalRooms")) {
            orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "totalRooms");
            sortOrder.add(orderSelected);
        }
        
        if (sortParam.getProperty().equals("size")) {
            orderSelected = new Order(sortParam.isAscending() ? Direction.ASC : Direction.DESC, "size");
            sortOrder.add(orderSelected);
        }

        if (sortOrder.isEmpty()) {
            sortOrder.add(orderDefault);
        }
        
        return sortOrder;
    }

    @Override
    public int size() {

        if (!StringUtils.isEmpty(userId)) {
            QRealState realStateQuery = new QRealState("realState");
            return Long.valueOf(realStateRepository.count(realStateQuery.userId.eq(userId))).intValue();
        } else {
            return Long.valueOf(realStateRepository.count()).intValue();
        }

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
