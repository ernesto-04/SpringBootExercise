package com.exercise.userservice.service;

import com.exercise.userservice.dto.CreateUserRequestDto;
import com.exercise.userservice.dto.CreateUserResponseDto;
import com.exercise.userservice.dto.UserDto;
import com.exercise.userservice.entity.User;
import com.exercise.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getName());
        long timestamp = new Date().getTime() * 1000;
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);
        
        userRepository.save(user);

        UserDto userDto = UserMapper.toUserDto(user);
        
        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setResult(true);
        responseDto.setUser(userDto);
        return responseDto;
    }

    // GET /users with pagination
    public List<UserDto> getUsers(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        List<User> users = userRepository.findAll(pageRequest).getContent();
        return users.stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
    }

    // GET /users/{id}
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserMapper.toUserDto(user.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
