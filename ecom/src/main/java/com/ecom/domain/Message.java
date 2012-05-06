package com.ecom.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 9139281899456825633L;

	@Id
	private ObjectId id;
    
    private String receiver;
    
    private String senderFirstname;

    private String senderLastName;

	private String senderTitle;
    
    private String senderEmail;
    
    private String senderPhone;
    
    private String senderCity;
    
    private String senderStreet;
    
    private String senderHouseNo;
    
    @Indexed
    private String subject;
    
    @Indexed
    private String messageBody;
    
    @Indexed
    private Date sentTs;
    
    private byte[] messagePayload;
    
    private String mimeType;
    
    private boolean isOpened = false;
    
    @Indexed
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

    public String getSenderFirstname() {
        return senderFirstname;
    }

    public void setSenderFirstname(String senderFirstname) {
        this.senderFirstname = senderFirstname;
    }

    
    public String getSenderLastName() {
		return senderLastName;
	}

	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
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
        return "Message [id=" + id + ", userId=" + receiver + ", sender=" + senderFirstname + ", subject=" + subject + ", message=" + messageBody + ", sentTs="
                + sentTs + ", messagePayload=" + Arrays.toString(messagePayload) + ", mimeType=" + mimeType + ", isOpened=" + isOpened
                + ", openedTs=" + openedTs + "]";
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderStreet() {
        return senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet;
    }

    public String getSenderHouseNo() {
        return senderHouseNo;
    }

    public void setSenderHouseNo(String senderHouseNo) {
        this.senderHouseNo = senderHouseNo;
    }

    public String getSenderTitle() {
        return senderTitle;
    }

    public void setSenderTitle(String senderTitle) {
        this.senderTitle = senderTitle;
    }

}
