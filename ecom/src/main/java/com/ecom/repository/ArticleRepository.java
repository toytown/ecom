package com.ecom.repository;

import org.bson.types.ObjectId;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ecom.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, ObjectId>, QueryDslPredicateExecutor<Article> {

}
