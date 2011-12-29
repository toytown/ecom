package com.ecom.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.domain.RealState;
import com.ecom.domain.RealStateImage;

public interface RealStateRepository extends PagingAndSortingRepository<RealState, ObjectId>,
		CrudRepository<RealState, ObjectId>, QueryDslPredicateExecutor<RealState> {

    @Query("{ 'images' : ?0 }")
	public RealStateImage findImagesById(String id); 
}
