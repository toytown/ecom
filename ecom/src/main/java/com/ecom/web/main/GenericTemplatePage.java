package com.ecom.web.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealState;
import com.ecom.web.components.image.StaticImage;

public abstract class GenericTemplatePage extends WebPage {

	private static final long serialVersionUID = 4433575012178589668L;

	@SpringBean
	private AppConfig appConfig;
	
	protected Image getTitleImage(RealState realState) {
		//return ImageResource.getImageData(realState.getTitleImage(), "png");
		return null;
	}
	
	protected StaticImage getTitleImageFromUrl(RealState realState) {
		return new StaticImage("title_image", new Model<String>(realState.getTitleImageLocation(appConfig.getImageRepository()))); 
	}
	
	protected Image getTitleImage() {
		return new Image("title_image", new AbstractReadOnlyModel<RenderedDynamicImageResource>() {

			private static final long serialVersionUID = -8788412636110253478L;

			@Override
			public RenderedDynamicImageResource getObject() {
				return new RenderedDynamicImageResource(50, 50) {

					private static final long serialVersionUID = 7881827809105145954L;

					@Override
					protected boolean render(Graphics2D g2) {

						BufferedImage baseIcon = null;
						BufferedImage baseIconOut = null;
						try {
							String imageFilePath = "C:/dev/gitRepository/ecom/src/main/webapp/images/p2.jpg";
							// String imageFilePath = titleImageDir + File.separator +
							// advert.getTitleImage();
							File imageFile = new File(imageFilePath);
							if (imageFile.canRead()) {
								baseIcon = ImageIO.read(imageFile);
								baseIconOut = ImageUtils.resize(baseIcon, 50, 50);
							} else {
								throw new RuntimeException("No image file found at " + imageFilePath);
							}

						} catch (IOException e) {
							throw new WicketRuntimeException(e);
						}
						g2.drawImage(baseIconOut, null, null);
						return true;
					}

				};
			}
		});
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
