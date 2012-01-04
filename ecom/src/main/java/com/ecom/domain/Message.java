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
    
    private Date sentTs;
    
    private byte[] messagePayload;
    
    private String mimeType;
    
    private boolean isOpened = false;
    
    private Date openedTs;

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Message(ObjectId id, String userId, String sender, String title, String message, Date sentTs) {
        super();
        this.id = id;
        this.userId = userId;
        this.sender = sender;
        this.title = title;
        this.message = message;
        this.sentTs = sentTs;
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

    public Date getSentTs() {
        return sentTs;
    }

    public void setSentTs(Date sentTs) {
        this.sentTs = sentTs;
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
		result = prime * result + (isOpened ? 1231 : 1237);
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + Arrays.hashCode(messagePayload);
		result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
		result = prime * result + ((openedTs == null) ? 0 : openedTs.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((sentTs == null) ? 0 : sentTs.hashCode());
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
		if (isOpened != other.isOpened)
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
		if (openedTs == null) {
			if (other.openedTs != null)
				return false;
		} else if (!openedTs.equals(other.openedTs))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (sentTs == null) {
			if (other.sentTs != null)
				return false;
		} else if (!sentTs.equals(other.sentTs))
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

	public boolean isOpened() {
		return isOpened;
	}

	public void setOpened(boolean isOpened) {
		this.isOpened = isOpened;
	}

	public Date getOpenedTs() {
		return openedTs;
	}

	public void setOpenedTs(Date openedTs) {
		this.openedTs = openedTs;
	}
}
