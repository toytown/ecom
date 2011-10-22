package com.ecom.web.main;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;

import com.ecom.common.utils.ImageResource;
import com.ecom.domain.RealState;

public abstract class GenericTemplatePage extends WebPage {

	private static final long serialVersionUID = 4433575012178589668L;

	protected Image getTitleImage(RealState realState) {
		return ImageResource.getImageData(realState.getTitleImage(), "png");
	}
}
