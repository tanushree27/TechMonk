package com.project.techmonk.userservice.service;

import com.project.techmonk.userservice.entity.UserDetails;
import com.project.techmonk.userservice.exception.UserNameAlreadyExistsException;
import com.project.techmonk.userservice.exception.UserNotFoundException;
import com.project.techmonk.userservice.exception.UserWithEmailAlreadyExistsException;
import com.project.techmonk.userservice.model.UserStatus;
import com.project.techmonk.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails createUser(String email, String username, String password, String fullName) {
        // validations
        userRepository.findByUsername(username).ifPresent(s -> {throw new UserNameAlreadyExistsException();});
        userRepository.findById(email).ifPresent(s -> {throw new UserWithEmailAlreadyExistsException();});

        // init and insert new user in DB
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(email);
        userDetails.setUsername(username);
        userDetails.setPassword(passwordEncoder.encode(password));
        userDetails.setFullName(fullName);
        userDetails.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(userDetails);
    }

    @Override
    public UserDetails getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserDetails> getUsers(List<String> emails) {
        return userRepository.findAllByEmailIn(emails);
    }
}
