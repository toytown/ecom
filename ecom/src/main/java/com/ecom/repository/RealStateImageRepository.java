package com.ecom.repository;

import org.bson.types.ObjectId;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.domain.RealStateImage;

public interface RealStateImageRepository extends PagingAndSortingRepository<RealStateImage, ObjectId>,
CrudRepository<RealStateImage, ObjectId>, QueryDslPredicateExecutor<RealStateImage> {


}

