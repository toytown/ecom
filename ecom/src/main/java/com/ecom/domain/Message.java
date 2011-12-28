package com.ecom.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 9139281899456825633L;

	private ObjectId id;
    
    private String userId;
    
    private String sender;
    
    private String title;
    
    private String message;
    
    private Date insertedTs;
    
    private byte[] messagePayload;
    
    private String mimeType;
    
    

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Message(ObjectId id, String userId, String sender, String title, String message, Date insertedTs) {
        super();
        this.id = id;
        this.userId = userId;
        this.sender = sender;
        this.title = title;
        this.message = message;
        this.insertedTs = insertedTs;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getInsertedTs() {
        return insertedTs;
    }

    public void setInsertedTs(Date insertedTs) {
        this.insertedTs = insertedTs;
    }

    public byte[] getMessagePayload() {
        return messagePayload;
    }

    public void setMessagePayload(byte[] messagePayload) {
        this.messagePayload = messagePayload;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((insertedTs == null) ? 0 : insertedTs.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + Arrays.hashCode(messagePayload);
        result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (insertedTs == null) {
            if (other.insertedTs != null)
                return false;
        } else if (!insertedTs.equals(other.insertedTs))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (!Arrays.equals(messagePayload, other.messagePayload))
            return false;
        if (mimeType == null) {
            if (other.mimeType != null)
                return false;
        } else if (!mimeType.equals(other.mimeType))
            return false;
        if (sender == null) {
            if (other.sender != null)
                return false;
        } else if (!sender.equals(other.sender))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
}
