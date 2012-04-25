package com.ecom.web.components.image;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

public final class StaticImage extends WebComponent {

	private static final long serialVersionUID = 2981332684886372369L;

	public StaticImage(String id, IModel<String> model) {
       super(id, model);
   }

	@Override
   protected void onComponentTag(ComponentTag tag) {
       super.onComponentTag(tag);
       checkComponentTag(tag, "img");
       tag.put("src", getDefaultModelObjectAsString());
       tag.put("alt", "");
   }

}