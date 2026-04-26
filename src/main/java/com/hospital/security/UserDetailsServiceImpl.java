package com.hospital.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospital.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    com.hospital.entity.User user =
	            userRepository.findByEmail(email.toLowerCase());

	    if (user == null) {
	        throw new UsernameNotFoundException("User not found");
	    }

	    return org.springframework.security.core.userdetails.User
	            .withUsername(user.getEmail())
	            .password(user.getPassword())
	            .authorities(
	                Arrays.stream(user.getRole().split(","))
	                        .map(role -> "ROLE_" + role.trim().toUpperCase())
	                        .toArray(String[]::new)
	            )
	            .build();
	}
}
