package com.ecom.common.utils;

import java.io.File;

public class AppConfig {

	private String imageStore;
	private String env;
	
    public String getImageStore() {
        return imageStore;
    }

    public void setImageStore(String imageStore) {
        this.imageStore = imageStore;
    }

    public File getImageStoreDir() {

        File imageStoreDir = new File(getImageStore());
        
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
