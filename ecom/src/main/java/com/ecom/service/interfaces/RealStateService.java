package com.ecom.service.interfaces;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ecom.domain.SearchRequest;

public interface RealStateService<RealState> extends Serializable {

	public Page<RealState> findBySearchRequest(SearchRequest req, PageRequest pageReq);
	
	public int count(SearchRequest req);
	
	public Page<RealState> findByUserSearchFilter(String userId, String filter, PageRequest pageReq);
	
	public int count(String userId, String filter);
	
}