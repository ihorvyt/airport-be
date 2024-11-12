package com.example.airport.service;

import com.example.airport.dto.AuthResponse;
import com.example.airport.dto.LoginRequest;
import com.example.airport.dto.RegisterRequest;
import com.example.airport.model.Role;
import com.example.airport.model.User;
import com.example.airport.repository.RoleRepository;
import com.example.airport.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.airport.utils.JwtUtil;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalArgumentException("USER role not found"));
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singleton(userRole));
        User savedUser = userRepository.save(user);

        String token = JwtUtil.generateToken(savedUser.getEmail());
        return new AuthResponse(token, savedUser.getId(), savedUser.getEmail());
    }


    public AuthResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty() || !passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = JwtUtil.generateToken(user.get().getEmail());
        return new AuthResponse(token, user.get().getId(), user.get().getEmail());
    }
}
