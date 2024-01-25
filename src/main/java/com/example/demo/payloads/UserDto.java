package com.example.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	
	private int id;
	
	
	@NotEmpty(message="username should not be null")
	@Size(min =4,max=10,message=" Min char=4 and max=10 for password should not be there")
	private String username;
	
	@NotEmpty(message="Password should not be null")
	@Size(min =3,max=10,message=" Min char=3 and max=10 for password should not be there")
	private String password;
	
	
	
}
