package com.ecom.repository;

import org.bson.types.ObjectId;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.domain.RealState;

public interface RealStateRepository extends PagingAndSortingRepository<RealState, ObjectId>,
		CrudRepository<RealState, ObjectId>, QueryDslPredicateExecutor<RealState> {

	
}
