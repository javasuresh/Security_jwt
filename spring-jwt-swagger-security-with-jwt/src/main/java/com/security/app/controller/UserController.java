package com.security.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.paylods.ApiResponse;
import com.security.app.paylods.UserDto;
import com.security.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		System.out.println(userDto);
		UserDto createUserDto = this.userService.createUser(userDto);
		System.out.println(createUserDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		
	}
	@PutMapping("/user/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Long userId){
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUser);
	}
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted successfully", true),HttpStatus.OK);
		
	}
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	
}
