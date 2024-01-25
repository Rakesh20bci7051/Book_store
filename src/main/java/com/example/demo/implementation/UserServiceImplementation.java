package com.example.demo.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.*;
import com.example.demo.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	
	//register new user
	@Override
	public UserDto registerNewUser(UserDto user) {
		// TODO Auto-generated method stub
		User obj=this.modelMapper.map(user, User.class);
		obj.setPassword(this.passwordEncoder.encode(obj.getPassword()));
		
		User newUser=this.userRepo.save(obj);
		return this.modelMapper.map(newUser, UserDto.class);
		
		
		
	}
	
	
	//changing UserDto to User
		private User dtoToUser(UserDto userDto)
		{
			User user=this.modelMapper.map(userDto,User.class); //convert directly
			/*user.setId(userDto.getId());
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setAbout(userDto.getAbout());
			user.setPassword(userDto.getPassword());*/
			return user;
		}


		public UserDto userToDto(User user)
		{
			UserDto obj=this.modelMapper.map(user, UserDto.class);
			/*obj.setId(user.getId());
			obj.setEmail(user.getEmail());
			obj.setName(user.getName());
			obj.setAbout(user.getAbout());
			obj.setPassword(user.getPassword());*/
			
			return obj;
		}

}
