package com.blog.apis.controllers;

import com.blog.apis.exaptions.ApiBadCredexaption;
import com.blog.apis.playloads.Authdto;
import com.blog.apis.playloads.UserDto;
import com.blog.apis.security.JwtHelper;
import com.blog.apis.security.JwtResponce;
import com.blog.apis.services.UserServices;
import com.blog.apis.utils.Responcehandler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserServices userServices;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<Object> createtoken(@RequestBody Authdto authdto) throws Exception {
		this.authanticate(authdto.getUsername(), authdto.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authdto.getUsername());
		String token = this.jwtHelper.generateToken(userDetails);
		Date tokenexp = this.jwtHelper.getExpirationDateFromToken(token);
		JwtResponce jwtResponce = new JwtResponce();
		jwtResponce.setToken(token);
		jwtResponce.setValidtill(tokenexp);
		return Responcehandler.generateResponse("Token Genrate Sucessfully", HttpStatus.OK, jwtResponce);

	}

	private void authanticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);

		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println(e.getMessage().toString());
			throw new ApiBadCredexaption("Username Or Password Invaid !");
		}
	}

	@PostMapping("/signup")
	private ResponseEntity<Object> signup(@RequestBody UserDto dto) {
		UserDto userDto = this.userServices.SignUp(dto);
		return Responcehandler.generateResponse("User Sign up SuccessFully", HttpStatus.OK, userDto);
	}


}
