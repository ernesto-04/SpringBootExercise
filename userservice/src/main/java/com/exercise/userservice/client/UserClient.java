package com.exercise.userservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exercise.userservice.dto.UserDto;

@Service
public class UserClient {

    private final RestTemplate restTemplate;

    @Autowired
    public UserClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public UserDto getUserById(Integer userId) {
        String url = "http://user-service/users/" + userId;
        return restTemplate.getForObject(url, UserDto.class);
    }
}
