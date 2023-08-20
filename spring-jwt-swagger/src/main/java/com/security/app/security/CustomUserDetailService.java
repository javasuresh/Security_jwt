package com.security.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.app.entity.User;
import com.security.app.exception.ResourceNotFoundException;
import com.security.app.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User users=this.userRepository.findByEmail(username).orElseThrow(()->
		new ResourceNotFoundException("User","Email: "+username,0));
		System.out.println("user email :"+users.getEmail());
		return users;
	}

}
