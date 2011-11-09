package com.ecom.web.main;

import java.io.File;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ecom.common.utils.AppConfig;
import com.ecom.domain.RealState;
import com.ecom.web.components.image.StaticImage;

public abstract class GenericTemplatePage extends WebPage {

	private static final long serialVersionUID = 4433575012178589668L;

	@SpringBean
	private AppConfig appConfig;
	

	protected StaticImage getTitleImageFromUrl(RealState realState) {
		return new StaticImage("title_image", new Model<String>(realState.getTitleImageLocation(appConfig.getImageRepository()))); 
	}
	

	
	protected Image getDefaultImage() {
		Image image = new Image("title_image", new ContextRelativeResource("images/empty_image.png")); 
		return image;
	}	
	
   protected File readImageFile(String fileName) throws Exception {
      Resource res = new ClassPathResource(fileName);
      File inputFile = res.getFile();
      
      if (inputFile.exists()) {
          return inputFile;
      }
      
      return null;
  } 
}
