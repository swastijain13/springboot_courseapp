package com.example.courseApplication.Service;

import com.example.courseApplication.Entity.User;
import com.example.courseApplication.Repository.UserRepository;
import com.example.courseApplication.config.JwtUtil;
import com.example.courseApplication.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("User already exists with username: " + user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("STUDENT");
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
            authManager.authenticate(auth);
            return jwtUtil.generateToken(username);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }
}
