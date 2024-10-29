package com.exercise.publicapi.dto;

public class CreateUserResponseDto {

    private boolean result;
    private UserDto user;

    // Getters and Setters
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
