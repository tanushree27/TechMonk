package com.project.techmonk.userservice.controller;

import com.project.techmonk.userservice.entity.UserDetails;
import com.project.techmonk.userservice.model.CreateUserRequest;
import com.project.techmonk.userservice.model.UserDetailsDTO;
import com.project.techmonk.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
    private UserService userService;

    @PostMapping("/users")
    public UserDetailsDTO signUp (@Valid @RequestBody CreateUserRequest createUserRequest) {
        log.info("Creating new user : {}", createUserRequest.getUsername());
        return parseUserDetails(userService.createUser(
                createUserRequest.getEmail(),
                createUserRequest.getUsername(),
                createUserRequest.getPassword(),
                createUserRequest.getFullName()
        ));
    }

    @Cacheable(value = "getUser")
    @GetMapping("/user/{username}")
    public UserDetailsDTO getUser (@PathVariable("username") String username) {
        return parseUserDetails(userService.getUser(username));
    }

    @Cacheable(value = "getUsers")
    @GetMapping("/users")
    public List<UserDetailsDTO> getUsers (@RequestParam("emails") List<String> emails) {
        if (emails.isEmpty())
            return new ArrayList<>();
        return userService.getUsers(emails).stream().map(this::parseUserDetails).toList();
    }

    private UserDetailsDTO parseUserDetails (UserDetails userDetails) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setEmail(userDetails.getEmail());
        userDetailsDTO.setFullName(userDetails.getFullName());
        userDetailsDTO.setUsername(userDetails.getUsername());
        userDetailsDTO.setUserStatus(userDetails.getUserStatus());
        return userDetailsDTO;
    }
}
