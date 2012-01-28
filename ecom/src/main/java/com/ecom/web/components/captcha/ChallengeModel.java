package com.ecom.web.components.captcha;

import java.io.Serializable;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class ChallengeModel extends AbstractReadOnlyModel<String>  implements Serializable {

	private static final long serialVersionUID = -5048606416975174405L;
	private String challenge;
    
    @Override
    public String getObject() {
        
        if (challenge == null) {
            challenge = String.format("%04d", (int) (Math.random() * 10000));
        }
        
        return challenge;
    }
    
    public void reset() {
        challenge = null;
    }

}
