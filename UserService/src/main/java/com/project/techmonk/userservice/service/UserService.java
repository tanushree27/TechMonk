package com.project.techmonk.userservice.service;

import com.project.techmonk.userservice.entity.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDetails createUser (String email, String username, String password, String fullName);
    UserDetails getUser (String username);
    List<UserDetails> getUsers (List<String> emails);
}
