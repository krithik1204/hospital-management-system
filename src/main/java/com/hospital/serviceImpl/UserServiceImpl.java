package com.hospital.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.entity.User;
import com.hospital.repository.UserRepository;
import com.hospital.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User createUser(String email, String password) {
		// TODO Auto-generated method stub
	User user=	User.builder().email(email).role("PATIENT").password(passwordEncoder.encode(password)).isActive(true).build();
		return userRepository.save(user);
	}

}
