package com.example.courseApplication.Service;
import com.example.courseApplication.Entity.User;
import com.example.courseApplication.Repository.UserRepository;
import com.example.courseApplication.config.JwtUtil;
import com.example.courseApplication.exceptions.UsernameAlreadyExistsException;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    UserRepository userRepo;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    AuthService authService;

    @Test
    void registerUserSuccessfully(){

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        String encodedPassword = "testEncodedPassword";

        when(passwordEncoder.encode("testPassword")).thenReturn(encodedPassword);

        String result = authService.register(user);

        assertEquals("User registered successfully", result);
        assertEquals("STUDENT", user.getRole());
        assertEquals(encodedPassword, user.getPassword());

    }
    @Test
    void registerUserFail(){
        User existingUser = new User();
        existingUser.setUsername("testuser1");

        when(userRepo.findByUsername("testuser1")).thenReturn(Optional.of(existingUser));

        User newUser = new User();
        newUser.setUsername("testuser1");
        newUser.setPassword("password");

        assertThrows(UsernameAlreadyExistsException.class, () -> authService.register(newUser));
        verify(userRepo, never()).save(any(User.class));
    }

    @Test
    void loginUserSuccessfully(){
        String username = "testuser";
        String password = "password";
        String token = "mocked-jwt-token";

        when(jwtUtil.generateToken(username)).thenReturn(token);

        String result = authService.login(username, password);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals(token, result);

    }

    @Test
    void loginUserFails(){
        String username = "wronguser";
        String password = "wrongpass";

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid Credentials"));

        assertThrows(BadCredentialsException.class, () -> {
            authService.login(username, password);
        });
    }
}
