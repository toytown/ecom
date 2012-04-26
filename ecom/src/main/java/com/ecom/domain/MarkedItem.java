package com.ecom.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "markedItems")
@CompoundIndexes({
    @CompoundIndex(name = "usrId_realstateId_idx", def = "{'userId': 1, 'realStateId': -1}")
})
public class MarkedItem implements Serializable {

    @Id
    private Object id;
    
    private Object itemId;
    
    private Object userId;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getItemId() {
        return itemId;
    }

    public void setItemId(Object itemId) {
        this.itemId = itemId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }
    
}
