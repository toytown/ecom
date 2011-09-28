package com.ecom.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.domain.Article;

public interface RealStateRepository extends PagingAndSortingRepository<Article, ObjectId>,
		CrudRepository<Article, ObjectId> {

}
