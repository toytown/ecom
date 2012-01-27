package com.ecom.web.components.captcha;

import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class ChallengeValidator implements IValidator<String> {

    private final IModel<String> challenge;
    
    public ChallengeValidator(IModel<String> challenge) {
        this.challenge = challenge;
    }

    @Override
    public void validate(IValidatable<String> validatable) {

        if (!challenge.getObject().equals(validatable.getValue())) {
            
            ValidationError error = new ValidationError();
            error.addMessageKey(getClass().getSimpleName());
            validatable.error(error);
        }
        
    }

}
