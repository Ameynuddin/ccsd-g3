package com.ccsdg3.ecom.service;

import com.ccsdg3.ecom.model.User;
import com.ccsdg3.ecom.repository.UserRepository;
import com.ccsdg3.ecom.dto.SignInRequest;
import com.ccsdg3.ecom.dto.SignUpRequest;
import com.ccsdg3.ecom.dto.AuthResponse;
import com.ccsdg3.ecom.exception.ResourceNotFoundException;
import com.ccsdg3.ecom.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        String token = jwtUtils.generateToken(user);
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(),
                user.isAdmin(), token);  // Changed from getIsAdmin() to isAdmin()
    }

    public AuthResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceNotFoundException("Email already exists");
        }

        User user = new User();
        us  er.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);

        String token = jwtUtils.generateToken(user);
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(),
                user.isAdmin(), token);
    }
}