package com.ecom.common.utils;

import org.springframework.stereotype.Component;

@Component("appConfig")
public class AppConfig {

	private String env;


    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }    
}
