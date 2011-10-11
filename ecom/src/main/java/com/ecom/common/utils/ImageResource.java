package com.ecom.common.utils;

import java.awt.image.BufferedImage;

import org.apache.wicket.request.resource.DynamicImageResource;

public class ImageResource extends DynamicImageResource {

    private static final long serialVersionUID = -3855409664242334118L;

    private byte[] image;

    public ImageResource(byte[] image, String format) {
        this.image = image;
        setFormat(format);
    }

    public ImageResource(BufferedImage image) {
        this.image = toImageData(image);
    }


    @Override
    protected byte[] getImageData(Attributes attributes) {
        if (image != null) {
            return image;
        } else {
            return new byte[0];
        }
    }

} 
