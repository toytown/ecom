package com.ecom.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ecom.domain.MarkedItem;
import com.ecom.domain.SearchRequest;

public interface RealStateService<RealState> extends Serializable {

	public Page<RealState> findBySearchRequest(SearchRequest req, PageRequest pageReq);
	
	public List<RealState> find(SearchRequest req, PageRequest pageReq);
	
	public RealState findOne(ObjectId id);
	
	public int count(SearchRequest req);
	
	public Page<RealState> findByUserSearchFilter(String userId, String filter, PageRequest pageReq);
	
	public int count(String userId, String filter);
	
	public void deleteRealState(RealState realState);
	
	public void activateRealState(RealState realState, Date activate);
	
	public void saveOrUpdate(RealState realState);
	
	public void saveMarkedItem(MarkedItem markedItem);
	
	public void deleteMarkedItem(MarkedItem markedItem);
	
	public void saveSearchResult(SearchRequest req);
	
	public void deleteSearchResult(SearchRequest req);	

   public boolean existsFreeAdvertLastMonthForUser(String userId);
}
