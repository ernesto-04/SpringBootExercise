package com.exercise.publicapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublicapiApplicationTests {

    @Value("${services.listing-service.url}")
    private String listingServiceUrl;

    @Value("${services.userservice.url}")
    private String userServiceUrl;

    @Test
    void contextLoads() {
        System.out.println("Listing Service URL: " + listingServiceUrl);
        System.out.println("User Service URL: " + userServiceUrl);
    }
}
