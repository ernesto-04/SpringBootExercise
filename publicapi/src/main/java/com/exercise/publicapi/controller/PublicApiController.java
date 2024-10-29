package com.exercise.publicapi.controller;

import com.exercise.publicapi.model.User;
import com.exercise.publicapi.model.UserWithListings;
import com.exercise.publicapi.dto.CreateListingRequestDto;
import com.exercise.publicapi.model.Listing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/public-api")
public class PublicApiController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${services.users.url}")
    private String userServiceUrl;

    @Value("${services.listings.url}")
    private String listingServiceUrl;

    // Endpoint to fetch user details
    @GetMapping("/listings")
    public ResponseEntity<?> getAllListings(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String userId) {

        // Fetch listings from the Listing Service
        String listingUrl = listingServiceUrl + "/listings?pageNum=" + pageNum + "&pageSize=" + pageSize;
        if (userId != null) {
            listingUrl += "&userId=" + userId;
        }

        // Retrieve listings
        Map<String, Object> listingResponse = webClientBuilder.build()
                .get()
                .uri(listingUrl)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, Object>> listings = (List<Map<String, Object>>) listingResponse.get("listings");

        // Fetch users from the User Service
        List<Map<String, Object>> users = webClientBuilder.build()
                .get()
                .uri(userServiceUrl + "/users")
                .retrieve()
                .bodyToMono(List.class)
                .block();

        // Combine listings with user info based on userId
        Map<Integer, Map<String, Object>> usersMap = users.stream()
                .collect(Collectors.toMap(user -> (Integer) user.get("id"), user -> user));

        listings.forEach(listing -> {
            Integer userIdFromListing = (Integer) listing.get("userId");
            Map<String, Object> user = usersMap.get(userIdFromListing);
            listing.put("user", user);
        });

        return ResponseEntity.ok(Map.of("result", true, "listings", listings));
    }
    
 // Create a New User
    @PostMapping(value = "/users", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> userData) {
        return webClientBuilder.build()
                .post()
                .uri(userServiceUrl + "/users")
                .bodyValue(userData)
                .retrieve()
                .toEntity(Map.class)
                .block();
    }
    
    // Create a new listings
    @PostMapping(value = "/listings", consumes = "application/json")
    public Mono<ResponseEntity<Map>> createListing(@RequestBody CreateListingRequestDto createListingRequest) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("userId", createListingRequest.getUserId().toString());
        formData.add("listingType", createListingRequest.getListingType());
        formData.add("price", createListingRequest.getPrice().toString());

        return webClientBuilder.build()
                .post()
                .uri(listingServiceUrl + "/listings")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .toEntity(Map.class);
    }


}
