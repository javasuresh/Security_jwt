package com.security.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.app.entity.User;
import com.security.app.exception.ResourceNotFoundException;
import com.security.app.paylods.UserDto;
import com.security.app.repository.UserRepository;
import com.security.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToEntity(userDto);
		User saveUser=this.userRepository.save(user);
		return this.entityToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Long userId) {
		User users=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		users.setName(user.getName());
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		users.setAbout(user.getAbout());
		User save = this.userRepository.save(users);
		UserDto entityToDto = this.entityToDto(save);
		return entityToDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User users=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return this.entityToDto(users);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> user = this.userRepository.findAll();
		List<UserDto> userDto=user.stream().map(users->this.entityToDto(users)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Long userId) {
		User users=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepository.delete(users);
		
	}
	
	private User dtoToEntity(UserDto user) {
		User users = this.mapper.map(user, User.class);
		
		/*
		 User users=new User();
		 users.setId(user.getId());
		users.setName(user.getName());
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		users.setAbout(user.getAbout());*/
		return users;
		
	}
	
	private UserDto entityToDto(User user) {
		UserDto users = this.mapper.map(user, UserDto.class);
		/*UserDto users=new UserDto();
		users.setId(user.getId());
		users.setName(user.getName());
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		users.setAbout(user.getAbout());*/
		return users;
		
	}

}
