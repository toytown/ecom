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
		
        double areaFrom = req.getAreaFrom() != null ? req.getAreaFrom().doubleValue() : 1.0;
        double areaTo = req.getAreaTo() != null ? req.getAreaTo().doubleValue() : 9999;   
        builder.and(realStateQuery.size.between(areaFrom, areaTo));
        
		double roomsFrom = req.getRoomsFrom() != null ? req.getRoomsFrom().doubleValue() : 0.0;
		double roomsTo = req.getRoomsTo() != null ? req.getRoomsTo().doubleValue() : 999;		
		builder.and(realStateQuery.totalRooms.between(roomsFrom, roomsTo));
		
		
		double priceFrom = req.getPriceFrom() != null ? req.getPriceFrom().doubleValue() : 0.0;
        double priceTo = req.getPriceTo() != null ? req.getPriceTo().doubleValue() : 999999999;       
        builder.and(realStateQuery.cost.between(priceFrom, priceTo));


		if (req.isProvisionFree() != null && req.isProvisionFree().booleanValue() == true) {
			builder.and(realStateQuery.provisionFree.eq(true));
		}

		if (req.isKitchenAvailable() != null && req.isKitchenAvailable().booleanValue() == true) {
			builder.and(realStateQuery.kitchenAvailable.eq(true));
		}
		
		if (req.isFurnished() != null && req.isFurnished() == true) {
			builder.and(realStateQuery.furnished.eq(true));
		}
		
		if (req.isBalconyAvailable() != null && req.isBalconyAvailable() == true) {
			builder.and(realStateQuery.balconyAvailable.eq(true));
		}
		
		if (req.isLiftAvailable() != null && req.isLiftAvailable() == true) {
			builder.and(realStateQuery.liftAvailable.eq(true));
		}
		
		if (req.isGardenAvailable() != null && req.isGardenAvailable().booleanValue()) {
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
