package com.blog.apis.controllers;

import com.blog.apis.Enitys.User;
import com.blog.apis.playloads.Authdto;
import com.blog.apis.playloads.UserDto;
import com.blog.apis.repo.UserRepo;
import com.blog.apis.security.JwtHelper;
import com.blog.apis.security.JwtResponce;
import com.blog.apis.services.UserServices;
import com.blog.apis.utils.Responcehandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
	private UserRepo userRepo ;

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserServices userServices;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<Object> createtoken(@RequestBody Authdto authdto) throws Exception {
		Boolean authanticate = this.authanticate(authdto.getUsername(), authdto.getPassword());
		if(authanticate)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(authdto.getUsername());
			String token = this.jwtHelper.generateToken(userDetails);
			Date tokenexp = this.jwtHelper.getExpirationDateFromToken(token);
			JwtResponce jwtResponce = new JwtResponce();
			jwtResponce.setToken(token);
			jwtResponce.setValidtill(tokenexp);
			return Responcehandler.generateResponse("Token Genrate Sucessfully", HttpStatus.OK, jwtResponce);
		}

		return Responcehandler.generateResponse("oops Somthing Worng ! Please Try Again.. ", HttpStatus.OK, "Invalid  Data");

	}

	private Boolean authanticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		return authenticate.isAuthenticated() ;
	}

	@PostMapping("/signup")
	private ResponseEntity<Object> signup( @Valid  @RequestBody UserDto dto) {
//		Boolean data = true ;
		User user = this.userRepo.findByEmail(dto.getEmail()).orElse( null);

		if(user !=null && user.getEmail().equals(dto.getEmail()))
		{
			return Responcehandler.generateResponse("Email Already Exist ! Try diffrent Email..", HttpStatus.OK,null);
		}
		else {
			UserDto userDto = this.userServices.SignUp(dto);
			return Responcehandler.generateResponse("User Sign up SuccessFully", HttpStatus.OK, userDto);
		}
	}


}
