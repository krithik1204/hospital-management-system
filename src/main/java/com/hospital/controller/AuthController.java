package com.hospital.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dto.request.LoginRequest;
import com.hospital.exception.ValidationException;
import com.hospital.security.config.JwtTokenUtil;

import jakarta.validation.Valid;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        logger.info("Login attempt for user: {}", email);
        
        // Validate input
        validateLoginRequest(email, password);
        
        // Attempt authentication - will throw AuthenticationException if failed
        // GlobalExceptionHandler will catch and handle it
        logger.info("Authenticating user with Spring Security...");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // If authentication is successful, set the context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("Authentication successful for user: {}", email);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_"))
                .toList();
        
        // Generate JWT token
        String token = jwtTokenUtil.generateToken(userDetails, roles);
        logger.info("JWT Token generated successfully for user: {}", userDetails.getUsername());
        
        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("email", userDetails.getUsername());
        response.put("roles", roles);
        response.put("token", token);
        response.put("message", "Login successful");

        return ResponseEntity.ok(response);
    }
    
    /**
     * Validate login request
     */
    private void validateLoginRequest(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("email", null, "Email is required and cannot be empty");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("password", null, "Password is required and cannot be empty");
        }
    }
}

