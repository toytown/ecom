package com.ecom.domain;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "markedItems")
@CompoundIndexes({ @CompoundIndex(name = "usrId_realstateId_idx", def = "{'userId': 1, 'realStateId': -1}") })
public class MarkedItem implements Serializable {

	private static final long serialVersionUID = 5949425921206769640L;

	@Id
	private ObjectId id;

	private ObjectId itemId;

	private ObjectId userId;

	private Date insertTs;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getItemId() {
		return itemId;
	}

	public void setItemId(ObjectId itemId) {
		this.itemId = itemId;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public Date getInsertTs() {
		return insertTs;
	}

	public void setInsertTs(Date insertTs) {
		this.insertTs = insertTs;
	}

}
