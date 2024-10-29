package com.exercise.userservice.controller;

import com.exercise.userservice.dto.CreateUserRequestDto;
import com.exercise.userservice.dto.CreateUserResponseDto;
import com.exercise.userservice.dto.UserDto;
import com.exercise.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // POST /users
    @PostMapping(value = "/users", consumes = "application/json")
    public CreateUserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    // GET /users
    @GetMapping("/users")
    public List<UserDto> getUsers(@RequestParam(defaultValue = "1") int pageNum, 
                                  @RequestParam(defaultValue = "10") int pageSize) {
        return userService.getUsers(pageNum, pageSize);
    }

    // GET /users/{id}
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
