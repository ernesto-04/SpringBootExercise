package com.exercise.publicapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "services")
public class ServicesConfig {

    private String listingServiceUrl;
    private String userServiceUrl;

    // Getters and setters
    public String getListingServiceUrl() {
        return listingServiceUrl;
    }

    public void setListingServiceUrl(String listingServiceUrl) {
        this.listingServiceUrl = listingServiceUrl;
    }

    public String getUserServiceUrl() {
        return userServiceUrl;
    }

    public void setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
