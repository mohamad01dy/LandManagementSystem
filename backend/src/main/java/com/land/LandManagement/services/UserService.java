package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.User;
import com.land.LandManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        //Check if user already exists
        user.setId(null);
        if (userRepository.findByEmail(user.getEmail()).equals(Optional.empty())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with this email");
    }

    public User getUserById(Integer id) {

        // Get email from the token
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch user to be returned from DB
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the authenticated user is the owner
        if (!targetUser.getEmail().equals(authenticatedEmail)) {
            throw new AccessDeniedException("You are not allowed to get other user record.");
        }
        return userRepository.getReferenceById(id);
    }

    public void updateUser(User user) {

        // Get email from the token
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch user to be updated from DB
        User targetUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the authenticated user is the owner
        if (!targetUser.getEmail().equals(authenticatedEmail)) {
            throw new AccessDeniedException("You are not allowed to fetch other users' data.");
        }

        //Hashed code, user can't change neither his nor his email
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(targetUser.getId());
        user.setEmail(targetUser.getEmail());
        userRepository.save(user);
    }

    public User getUserByToken() {
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User targetUser = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return targetUser;
    }
}
