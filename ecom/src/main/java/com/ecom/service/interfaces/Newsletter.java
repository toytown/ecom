package com.ecom.service.interfaces;

import com.ecom.domain.Subscriber;

public interface Newsletter {

	public void save(Subscriber subscriber);
	
	public void sendNewsletter();
}
