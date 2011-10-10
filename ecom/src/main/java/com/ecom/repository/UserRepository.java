package com.ecom.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId>, CrudRepository<User, ObjectId> {

}
