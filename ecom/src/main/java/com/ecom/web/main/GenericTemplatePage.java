package com.ecom.web.main;

import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.ResourceReference;

import com.ecom.domain.RealState;
import com.ecom.web.components.image.EcomImageResouceReference;
import com.ecom.web.components.image.StaticImage;

public abstract class GenericTemplatePage extends WebPage {

	public GenericTemplatePage() {
        super();
    }


    private static final long serialVersionUID = 4433575012178589668L;

	protected StaticImage getTitleImageFromUrl(String url) {

		return new StaticImage("title_image", new Model<String>(url)); 
	}
	
	protected StaticImage getTitleImageFromUrl(RealState realState) {

		final ResourceReference imagesResourceReference = new EcomImageResouceReference();
		final PageParameters imageParameters = new PageParameters();
		String imageId = realState.getTitleThumbNailImage();
		imageParameters.set("id", imageId);

		CharSequence urlForWordAsImage = getRequestCycle().urlFor(imagesResourceReference, imageParameters);
		return new StaticImage("title_image", new Model<String>(urlForWordAsImage.toString())); 
	}
	
	
	protected Image getDefaultImage() {
		Image image = new Image("title_image", new ContextRelativeResource("images/empty_image.png")); 
		return image;
	}	
	

}
