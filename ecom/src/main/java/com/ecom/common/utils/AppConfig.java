package com.ecom.common.utils;

import java.io.File;

public class AppConfig {

	private String imageRepository;
	private String env;
	
    public String getImageRepository() {
        return imageRepository;
    }

    public void setImageRepository(String imageStore) {
        this.imageRepository = imageStore;
    }

    public File getImageStoreDir() {

        File imageStoreDir = new File(getImageRepository());
        
        if (!imageStoreDir.exists()) {
            if (imageStoreDir.mkdirs()) {
                throw new RuntimeException("Image Store directory at location " + imageStoreDir.getAbsolutePath() + " could not be created");
            }
        }
        
        return imageStoreDir;        
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }    
}
