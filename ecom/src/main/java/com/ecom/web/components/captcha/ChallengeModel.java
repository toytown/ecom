package com.ecom.web.components.captcha;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class ChallengeModel extends AbstractReadOnlyModel<String> {

    private String challenge;
    
    @Override
    public String getObject() {
        
        if (challenge == null) {
            challenge = String.format("%04d", (int) (Math.random() * 10233));
        }
        
        return challenge;
    }
    
    public void reset() {
        challenge = null;
    }

}
