package com.ecom.web.components.image;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;

import com.ecom.web.utils.HtmlElementEnum;

public class ImageNavigationPanel extends Panel {



	public ImageNavigationPanel(String id, List<String> imagesUrls) {
		super(id, Model.of(imagesUrls));
	}

	public ImageNavigationPanel(String id, IModel<List<String>> imagesUrlsModel) {
		super(id, imagesUrlsModel);

	}
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new GalleriaBehavior(0, 320));

		ListView<String> imagesView = new ListView<String>("images", (IModel<? extends List<? extends String>>)getDefaultModel()) {

			private static final long serialVersionUID = 5834906931354896004L;

			@Override
			protected void populateItem(ListItem<String> item) {
				String imageURL = item.getModelObject();
				WebMarkupContainer container = new WebMarkupContainer("link");
				container.add(AttributeModifier.replace("href", imageURL));
				item.add(container);
				StaticImage image = new StaticImage("image", new Model<String>(imageURL));
				container.add(image);				
			}
		};
		add(imagesView);
		
	}
	
	public static String toAbsoluteImagePath(String sharedResource, String absoluteImagePath) {
		String url;
		if (absoluteImagePath.startsWith(HtmlElementEnum.PROTOCOL_HTTP.value())) {
			url = absoluteImagePath;
		} else {
			ResourceReference imageResource = new SharedResourceReference(sharedResource);
			PageParameters params = new PageParameters();
			params.set(0, absoluteImagePath);
			//HttpServletRequest req = (HttpServletRequest)RequestCycle.get().getRequest().getContainerRequest();
			url = RequestCycle.get().getUrlRenderer().renderFullUrl(RequestCycle.get().mapUrlFor(imageResource, params));
		}
		return url;
	}
}
