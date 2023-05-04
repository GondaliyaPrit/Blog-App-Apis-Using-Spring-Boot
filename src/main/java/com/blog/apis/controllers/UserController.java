package com.blog.apis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.playloads.UserDto;
import com.blog.apis.services.UserServices;
import com.blog.apis.utils.Responcehandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired(required = true)
	private UserServices services;

	@PostMapping("/")
	public ResponseEntity<Object>  createuser( @Valid @RequestBody UserDto userDto) {
		UserDto userDto2 = this.services.Createuser(userDto);
		return Responcehandler.generateResponse("User added", HttpStatus.OK, userDto2);
	}

	@PutMapping("/{userid}")
	public ResponseEntity<Object> updateuser( @Valid @RequestBody UserDto userDto, @PathVariable Integer userid) {

		UserDto updateduser = this.services.Updateuser(userDto, userid);
		return  Responcehandler.generateResponse("User Update Successfully",HttpStatus.OK, updateduser);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{userid}")
	public ResponseEntity<Object> deleteuser(@PathVariable Integer userid) {
		this.services.Deluser(userid);
		return Responcehandler.generateResponse("User Delete Sucessfully", HttpStatus.OK, userid);
	}

	@GetMapping("/")
	public ResponseEntity<Object> getalluser() {
		return Responcehandler.generateResponse("Users Get Sucessfully", HttpStatus.OK, this.services.Getallusers());
	}

	@GetMapping("/{userid}")
	public ResponseEntity<Object> getuser(@PathVariable Integer userid) {
		UserDto getuser = this.services.Getuserbyid(userid);
		
	
		return Responcehandler.generateResponse("User Get Successfully ", HttpStatus.OK, getuser);			
		
	}

}
