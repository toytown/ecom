package com.ecom.common.utils;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.BufferedDynamicImageResource;

public class ImageResource {

    public static Image getImageData(byte[] imageBytes, String format) {
        BufferedDynamicImageResource bufferedDynamicImage = new BufferedDynamicImageResource();
        bufferedDynamicImage.setFormat(format);
        
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            bufferedDynamicImage.setImage(bufferedImage);
            Image image = new Image("title_image", bufferedDynamicImage); 
            return image;
        } catch (IOException e) {
            throw new RuntimeException("IOException occured during IOException " + e.getMessage());
        }
    }

} 
