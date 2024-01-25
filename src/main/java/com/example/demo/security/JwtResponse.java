package com.example.demo.security;

import com.example.demo.payloads.UserDto;
import com.example.demo.security.JwtResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

	private String jwtToken;
	private String username;
	private UserDto user;
}
