package com.exercise.publicapi.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.exercise.publicapi.dto.CreateListingRequestDto;
import com.exercise.publicapi.dto.CreateListingResponseDto;
import com.exercise.publicapi.dto.CreateUserRequestDto;
import com.exercise.publicapi.dto.CreateUserResponseDto;
import com.exercise.publicapi.dto.GetListingsResponseDto;
import com.exercise.publicapi.service.PublicApiService;
import reactor.core.publisher.Mono;

@Service
public class PublicApiServiceImpl implements PublicApiService {

    @Value("${services.listings.url}")
    private String listingServiceUrl;

    @Value("${services.users.url}")
    private String userServiceUrl;

    private final WebClient webClient;

    public PublicApiServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public CreateListingResponseDto createListing(CreateListingRequestDto requestDto) {
        return webClient.post()
                .uri(listingServiceUrl)
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(CreateListingResponseDto.class)
                .block();
    }

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        return webClient.post()
                .uri(userServiceUrl)
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(CreateUserResponseDto.class)
                .block();
    }

    @Override
    public GetListingsResponseDto getListings(Integer pageNum, Integer pageSize, Integer userId) {
        String url = listingServiceUrl + "?pageNum=" + pageNum + "&pageSize=" + pageSize;
        if (userId != null) {
            url += "&userId=" + userId;
        }
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(GetListingsResponseDto.class)
                .block();
    }
}
