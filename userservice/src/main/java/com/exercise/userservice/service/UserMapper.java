package com.exercise.userservice.service;

import com.exercise.userservice.dto.UserDto;
import com.exercise.userservice.entity.User;

public class UserMapper {
	public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }
}
