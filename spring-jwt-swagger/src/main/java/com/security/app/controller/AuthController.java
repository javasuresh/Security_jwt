package com.security.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.exception.ApiException;
import com.security.app.paylods.JwtAuthRequest;
import com.security.app.paylods.JwtAuthResponse;
import com.security.app.paylods.UserDto;
import com.security.app.security.JwtTokenHelper;
import com.security.app.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		this.authenticate(request.getUsername(), request.getPassword());
	UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
	String token = this.jwtTokenHelper.generateToken(userDetails);
	JwtAuthResponse res=new JwtAuthResponse();
	res.setToken(token);
	return new ResponseEntity<JwtAuthResponse>(res,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, password);
	
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invailed details !!");
			throw new ApiException("Invalied username or password !!");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser
	(@RequestBody UserDto userDto){
	UserDto registerNewUser = this.userService.registerNewUser(userDto);	
	return new ResponseEntity<UserDto>(registerNewUser,HttpStatus.CREATED);
	}
}
