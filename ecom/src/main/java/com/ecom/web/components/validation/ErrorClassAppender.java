package com.ecom.web.components.validation;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

public class ErrorClassAppender extends Behavior {

	private static final long serialVersionUID = 3271031827487220951L;

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		
		if (((FormComponent<?>)component).isValid() == false) {
		
			String c1 = tag.getAttribute("class");
			
			if (c1 == null) {
				tag.put("class", "formcomponent invalid");				
			} else {
				tag.put("class", "formcomponent invalid " + c1);	
			}
		}
	}
}
