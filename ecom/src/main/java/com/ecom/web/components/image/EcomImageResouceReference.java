package com.ecom.web.components.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.io.ByteArrayOutputStream;

import com.ecom.service.interfaces.ImageService;

public class EcomImageResouceReference extends ResourceReference {

	private static final long serialVersionUID = 1L;

	public EcomImageResouceReference() {
		super(EcomImageResouceReference.class, "ecomRef");

	}

	@Override
	public IResource getResource() {
		return new ImageResource();
	}

	/**
	 * A resource which shows how to return back the image as bytes. For the demo
	 * it generates the image on the fly but in real life the image can be read
	 * from DB, file system, Internet, ...
	 */
	private static class ImageResource extends DynamicImageResource {

		private static final long serialVersionUID = 1L;

		@SpringBean
		private ImageService imageService;

		public ImageResource() {
			Injector.get().inject(this);
		}

		@Override
		protected byte[] getImageData(Attributes attributes) {

			PageParameters parameters = attributes.getParameters();
			byte[] imageBytes = null;
			org.apache.wicket.util.string.StringValue id = parameters.get("id");

			if (id.isEmpty() == false) {
				try {
					imageBytes = getImageAsBytes(id.toString());

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			return imageBytes;
		}


		private byte[] getImageAsBytes(String id) {

			BufferedImage large_image = null;
			try {
				InputStream in  = imageService.getImageAsBytes(id);
				large_image = ImageIO.read(in);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = writers.next();
			if (writer == null) {
				throw new RuntimeException("JPG not supported?!");
			}

			final ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] imageBytes = null;
			try {

				ImageOutputStream imageOut = ImageIO.createImageOutputStream(out);
				writer.setOutput(imageOut);
				writer.write(large_image);
				imageOut.close();
				imageBytes = out.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return imageBytes;

		}

		@Override
		public boolean equals(Object that) {
			return that instanceof ImageResource;
		}

	}

}
