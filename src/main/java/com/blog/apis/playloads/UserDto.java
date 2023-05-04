package com.blog.apis.playloads;


import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;

	@NotEmpty
	@Length(min = 4, max = 16, message = "Username Must be min 4 Characters ")
	private String name;
	@NotNull
	private String password;
	@NotEmpty
	private String about;
	@NotEmpty
	@Email(message = "Invalid Email")
	private String email;
	
	List<PostDto> posts ;
	
	


}
