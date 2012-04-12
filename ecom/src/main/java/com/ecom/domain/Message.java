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
    
    private String receiver;
    
    private String sender;
    
    private String subject;
    
    private String messageBody;
    
    private Date sentTs;
    
    private byte[] messagePayload;
    
    private String mimeType;
    
    private boolean isOpened = false;
    
    private Date openedTs;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", userId=" + receiver + ", sender=" + sender + ", subject=" + subject + ", message=" + messageBody + ", sentTs="
                + sentTs + ", messagePayload=" + Arrays.toString(messagePayload) + ", mimeType=" + mimeType + ", isOpened=" + isOpened
                + ", openedTs=" + openedTs + "]";
    }

}
