package com.land.LandManagement.services;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.domain.tables.User;
import com.land.LandManagement.repositories.LandRepository;
import com.land.LandManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.getReferenceById(id);
    }

    public void updateUser(User user) {
        getUser(user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
