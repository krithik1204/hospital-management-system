package com.hospital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospital.entity.User;
import com.hospital.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

	   com.hospital.entity.User user = userRepository.findByEmail(email.toLowerCase());

		UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());

		userBuilder.password(user.getPassword());
		String prefixedRole = "ROLE_" + user.getRole().toString().toUpperCase();
		userBuilder.authorities(prefixedRole);
		return userBuilder.build();
	}

}
