package com.exercise.publicapi.service;

import com.exercise.publicapi.dto.*;

public interface PublicApiService {
    CreateListingResponseDto createListing(CreateListingRequestDto requestDto);
    CreateUserResponseDto createUser(CreateUserRequestDto requestDto);
    GetListingsResponseDto getListings(Integer pageNum, Integer pageSize, Integer userId);
}
