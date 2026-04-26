package com.hospital.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.entity.User;
import com.hospital.exception.UserAlreadyExistsException;
import com.hospital.exception.ValidationException;
import com.hospital.repository.UserRepository;
import com.hospital.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User createUser(String email, String password) {
		logger.info("Creating user with email: {}", email);
		
		// Validate email and password
		validateEmailAndPassword(email, password);
		
		// Check if user already exists
		User existingUser = userRepository.findByEmail(email);
		if (existingUser != null) {
			logger.error("User with email {} already exists", email);
			throw new UserAlreadyExistsException(email, 
				"A user with email '" + email + "' already exists. Please use a different email.");
		}
		
		try {
			User user = User.builder()
				.email(email)
				.role("PATIENT")
				.password(passwordEncoder.encode(password))
				.isActive(true)
				.build();
			
			User savedUser = userRepository.save(user);
			logger.info("User created successfully with email: {}", email);
			return savedUser;
		} catch (Exception e) {
			logger.error("Error occurred while creating user: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to create user. Please try again.");
		}
	}
	
	/**
	 * Validate email and password
	 */
	private void validateEmailAndPassword(String email, String password) {
		if (email == null || email.trim().isEmpty()) {
			throw new ValidationException("email", null, "Email is required and cannot be empty");
		}
		
		if (password == null || password.trim().isEmpty()) {
			throw new ValidationException("password", null, "Password is required and cannot be empty");
		}
		
		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new ValidationException("email", email, "Invalid email format");
		}
		
		if (password.length() < 6) {
			throw new ValidationException("password", null, "Password must be at least 6 characters long");
		}
	}

}
