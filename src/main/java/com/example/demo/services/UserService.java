package com.example.demo.services;

import com.example.demo.payloads.UserDto;

public interface UserService {

	//register a new user
		UserDto registerNewUser(UserDto user);
		 UserDto getUserById(Integer userId);
}
