package com.ecom.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import com.ecom.common.utils.GeoLocationUtils;
import com.ecom.domain.GeoLocation;
import com.ecom.domain.MarkedItem;
import com.ecom.domain.QRealState;
import com.ecom.domain.RealState;
import com.ecom.domain.SearchRequest;
import com.ecom.repository.MarkedItemRepository;
import com.ecom.repository.RealStateRepository;
import com.ecom.repository.SearchRequestRepository;
import com.ecom.service.interfaces.GeoLocationService;
import com.ecom.service.interfaces.ImageService;
import com.ecom.service.interfaces.RealStateService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

@Service("realStateService")
public class RealStateServiceImpl implements RealStateService<RealState> {

	private static final long serialVersionUID = 7179912056888874311L;

	private static final double MIN_VAL = 0.0;

	private static final double MAX_VAL = 999.0;

	private static final double MAX_PRICE = 9999999999.0;

	@Autowired
	private RealStateRepository realStateRepository;

	@Autowired
	private MarkedItemRepository markedItemRepository;

	@Autowired
	private SearchRequestRepository searchReqRepository;

	@Autowired
	private ImageService imageService;

	@Autowired
	private GeoLocationService geoLocationService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Page<RealState> findBySearchRequest(SearchRequest req,
			PageRequest pageReq) {
		return realStateRepository.findAll(buildPredicate(req), pageReq);
	}

	@Override
	public List<RealState> find(SearchRequest req, PageRequest pageReq) {
		Query q = buildQuery(req);
		return mongoTemplate.find(QueryUtils.applyPagination(q, pageReq),
				RealState.class);
	}

	public Query buildQuery(SearchRequest req) {
		Query q = new Query();

		String zipOrCityLoc = req.getLocation();
		if (StringUtils.isNotEmpty(zipOrCityLoc)) {
			GeoLocation geoLoc = geoLocationService.findOne(zipOrCityLoc);
			Point point = new Point(geoLoc.getLat(), geoLoc.getLng());
			q.addCriteria(Criteria.where("location").nearSphere(point)
					.maxDistance(0.01));
		}

		double areaFrom = req.getAreaFrom() != null ? req.getAreaFrom()
				.doubleValue() : MIN_VAL;
		double areaTo = req.getAreaTo() != null ? req.getAreaTo().doubleValue()
				: MAX_VAL;

		double roomsFrom = req.getRoomsFrom() != null ? req.getRoomsFrom()
				.doubleValue() : MIN_VAL;
		double roomsTo = req.getRoomsTo() != null ? req.getRoomsTo()
				.doubleValue() : MAX_VAL;

		double priceFrom = req.getPriceFrom() != null ? req.getPriceFrom()
				.doubleValue() : MIN_VAL;
		double priceTo = req.getPriceTo() != null ? req.getPriceTo()
				.doubleValue() : MAX_PRICE;
				
		q.addCriteria(Criteria.where("totalRooms").gte(roomsFrom)
				.andOperator(Criteria.where("totalRooms").lte(roomsTo)));
		q.addCriteria(Criteria.where("size").gte(areaFrom)
				.andOperator(Criteria.where("size").lte(areaTo)));
		q.addCriteria(Criteria.where("cost").gte(priceFrom)
				.andOperator(Criteria.where("cost").lte(priceTo)));

		return q;
	}

	public Predicate buildPredicate(SearchRequest req) {

		QRealState realStateQuery = new QRealState("realStateUser");
		BooleanBuilder builder = new BooleanBuilder();

		if (req == null) {
			return builder.and(realStateQuery.id.eq(new ObjectId()));
		}

		if (StringUtils.isNotEmpty(req.getLocation())) {
			if (GeoLocationUtils.isZipCodeOnly(req.getLocation())) {
				builder.and(realStateQuery.areaCode.contains(req.getLocation()));
			} else {
				builder.and(realStateQuery.city.containsIgnoreCase(req
						.getLocation()));
			}
		}

		double areaFrom = req.getAreaFrom() != null ? req.getAreaFrom()
				.doubleValue() : MIN_VAL;
		double areaTo = req.getAreaTo() != null ? req.getAreaTo().doubleValue()
				: MAX_VAL;
		builder.and(realStateQuery.size.between(areaFrom, areaTo));

		double roomsFrom = req.getRoomsFrom() != null ? req.getRoomsFrom()
				.doubleValue() : MIN_VAL;
		double roomsTo = req.getRoomsTo() != null ? req.getRoomsTo()
				.doubleValue() : MAX_VAL;
		builder.and(realStateQuery.totalRooms.between(roomsFrom, roomsTo));

		double priceFrom = req.getPriceFrom() != null ? req.getPriceFrom()
				.doubleValue() : MIN_VAL;
		double priceTo = req.getPriceTo() != null ? req.getPriceTo()
				.doubleValue() : MAX_VAL;
		builder.and(realStateQuery.cost.between(priceFrom, priceTo));

		if (req.isProvisionFree() != null
				&& req.isProvisionFree().booleanValue() == true) {
			builder.and(realStateQuery.provisionFree.eq(true));
		}

		if (req.isKitchenAvailable() != null
				&& req.isKitchenAvailable().booleanValue() == true) {
			builder.and(realStateQuery.kitchenAvailable.eq(true));
		}

		if (req.isFurnished() != null && req.isFurnished() == true) {
			builder.and(realStateQuery.furnished.eq(true));
		}

		if (req.isBalconyAvailable() != null
				&& req.isBalconyAvailable() == true) {
			builder.and(realStateQuery.balconyAvailable.eq(true));
		}

		if (req.isElevatorAvailable() != null
				&& req.isElevatorAvailable() == true) {
			builder.and(realStateQuery.elevatorAvailable.eq(true));
		}

		if (req.isGardenAvailable() != null
				&& req.isGardenAvailable().booleanValue()) {
			builder.and(realStateQuery.garageAvailable.eq(true));
		}
		return builder;

	}

	public Predicate buildPredicate(String userId, String filterVal) {
		QRealState realStateQuery = new QRealState("realStateUser");
		BooleanBuilder builder = new BooleanBuilder();

		Predicate condition = null;
		if (!StringUtils.isEmpty(filterVal) && !StringUtils.isEmpty(userId)) {
			builder.or(realStateQuery.city.contains(filterVal));
			builder.or(realStateQuery.street.contains(filterVal));
			builder.or(realStateQuery.areaCode.contains(filterVal));
			if (ObjectId.isValid(filterVal)) {
				builder.or(realStateQuery.id.eq(new ObjectId(filterVal)));
			}
		}

		condition = builder.and(realStateQuery.userId.eq(userId));
		return condition;
	}

	@Override
	public int count(SearchRequest req) {
		// return (int) realStateRepository.count(buildPredicate(req));
		return (int) mongoTemplate.count(buildQuery(req), RealState.class);
	}

	@Override
	public Page<RealState> findByUserSearchFilter(String userId, String filter,
			PageRequest pageReq) {

		return realStateRepository.findAll(buildPredicate(userId, filter),
				pageReq);
	}

	@Override
	public int count(String userId, String filter) {
		return (int) realStateRepository.count(buildPredicate(userId, filter));

	}

	@Override
	public void deleteRealState(RealState realState) {
		imageService.deleteAllImages(realState);
		realStateRepository.delete(realState);

	}

	@Override
	public void activateRealState(RealState realState, Date activationDate) {
		realState.activate(activationDate);
		realStateRepository.save(realState);

	}

	@Override
	public void saveOrUpdate(RealState realState) {
		realStateRepository.save(realState);

	}

	@Override
	public void saveMarkedItem(MarkedItem markedItem) {
		markedItemRepository.save(markedItem);
	}

	@Override
	public void deleteMarkedItem(MarkedItem markedItem) {
		markedItemRepository.delete(markedItem);
	}

	@Override
	public RealState findOne(ObjectId id) {
		return realStateRepository.findOne(id);
	}

	@Override
	public void saveSearchResult(SearchRequest req) {
		searchReqRepository.delete(req);

	}

	@Override
	public void deleteSearchResult(SearchRequest req) {
		searchReqRepository.delete(req);

	}

	@Override
	public boolean existsFreeAdvertLastMonthForUser(String userId) {

		QRealState realStateQuer = new QRealState("realState");
		Calendar cal = Calendar.getInstance();
		cal.add(-1 * Calendar.DAY_OF_MONTH, 30);

		Predicate condition = realStateQuer.userId.eq(userId).and(
				realStateQuer.insertedTs.loe(cal.getTime()));
		long countVal = realStateRepository.count(condition);

		return countVal > 0;
	}

}
