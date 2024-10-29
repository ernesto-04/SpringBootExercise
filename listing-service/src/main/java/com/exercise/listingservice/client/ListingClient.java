package com.exercise.listingservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exercise.listingservice.dto.ListingDto;

@Service
public class ListingClient {

    private final RestTemplate restTemplate;

    @Autowired
    public ListingClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ListingDto getListingById(Integer listingId) {
        String url = "/listings/{id}" + listingId;
        return restTemplate.getForObject(url, ListingDto.class);
    }
}
