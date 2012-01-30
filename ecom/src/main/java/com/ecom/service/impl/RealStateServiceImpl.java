package com.ecom.service.impl;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ecom.domain.QRealState;
import com.ecom.domain.RealState;
import com.ecom.domain.SearchRequest;
import com.ecom.repository.RealStateRepository;
import com.ecom.service.interfaces.ImageService;
import com.ecom.service.interfaces.RealStateService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

@Service("realStateService")
public class RealStateServiceImpl implements RealStateService<RealState> {

	private static final long serialVersionUID = 7179912056888874311L;
	
	@Autowired
	private RealStateRepository realStateRepository;

	@Autowired
	private ImageService imageService;
	
	@Override
	public Page<RealState> findBySearchRequest(SearchRequest req, PageRequest pageReq) {
		return realStateRepository.findAll(buildPredicate(req), pageReq);
	}

	public Predicate buildPredicate(SearchRequest req) {
		QRealState realStateQuery = new QRealState("realStateUser");
		BooleanBuilder builder = new BooleanBuilder();

		if (req == null) {
			return builder.and(realStateQuery.id.eq(new ObjectId()));
		}
		
		if (req.getAreaFrom() != null && req.getAreaFrom() > 0.0) {
			builder.and(realStateQuery.size.goe(req.getAreaFrom()));
		}

		if (req.getAreaTo() != null && req.getAreaTo() > 0.0) {
			builder.and(realStateQuery.size.loe(req.getAreaTo()));
		}

		if (req.getRoomsFrom() != null && req.getRoomsFrom() > 0.0) {
			builder.and(realStateQuery.totalRooms.goe(req.getRoomsFrom()));
		}

		if (req.getRoomsTo() != null && req.getRoomsTo() > 0.0) {
			builder.and(realStateQuery.totalRooms.loe(req.getRoomsTo()));
		}

		if (req.getPriceFrom() != null && req.getPriceFrom() > 0.0) {
			builder.and(realStateQuery.cost.goe(req.getPriceFrom()));
		}

		if (req.getPriceTo() != null && req.getPriceTo() > 0.0) {
			builder.and(realStateQuery.cost.loe(req.getPriceTo()));
		}

		if (req.isProvisionFree()) {
			builder.and(realStateQuery.provisionFree.eq(true));
		}

		if (req.isKitchenAvailable()) {
			builder.and(realStateQuery.kitchenAvailable.eq(true));
		}
		
		if (req.isFurnished()) {
			builder.and(realStateQuery.furnished.eq(true));
		}
		
		if (req.isBalconyAvailable()) {
			builder.and(realStateQuery.balconyAvailable.eq(true));
		}
		
		if (req.isLiftAvailable()) {
			builder.and(realStateQuery.liftAvailable.eq(true));
		}
		
		if (req.isGardenAvailable()) {
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
		return (int) realStateRepository.count(buildPredicate(req));
	}

	@Override
	public Page<RealState> findByUserSearchFilter(String userId, String filter, PageRequest pageReq) {
		return realStateRepository.findAll(buildPredicate(userId, filter), pageReq);		
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
}
