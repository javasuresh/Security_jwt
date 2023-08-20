package com.security.app.service;

import java.util.List;

import com.security.app.paylods.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Long userId);
	UserDto getUserById(Long userId);
	List<UserDto> getAllUsers();
	void deleteUser(Long userId);
	
	

}
