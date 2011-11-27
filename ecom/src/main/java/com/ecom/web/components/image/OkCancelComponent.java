package com.ecom.web.components.image;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

public class OkCancelComponent extends WebComponent {

	private static final long serialVersionUID = -7594735953102473532L;

	public OkCancelComponent(String id, IModel<Object> iModel) {
		super(id, iModel);
	}



	@Override
   protected void onComponentTag(ComponentTag tag) {
		Boolean yesNoValue = (Boolean) getDefaultModelObject();
		
       super.onComponentTag(tag);
       checkComponentTag(tag, "img");
       if (yesNoValue != null && yesNoValue.booleanValue() == true) {
      	 tag.put("src", "images/buttons/ok_16x16_mini.png");

       } else {
      	 tag.put("src", "images/buttons/bullet_cross.png");
       }
   }	
}
