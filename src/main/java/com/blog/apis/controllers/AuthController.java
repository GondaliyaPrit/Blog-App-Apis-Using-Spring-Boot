package com.blog.apis.controllers;

import com.blog.apis.playloads.Authdto;
import com.blog.apis.security.JwtHelper;
import com.blog.apis.security.JwtResponce;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

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
    private JwtHelper jwtHelper ;

@Autowired
private  UserDetailsService userDetailsService  ;

@Autowired
private AuthenticationManager authenticationManager ;
    @PostMapping("/login")
    public ResponseEntity<JwtResponce> createtoken(@RequestBody Authdto authdto )
    {
       this.authanticate(authdto.getUsername(),authdto.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authdto.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);
        Date tokenexp = this.jwtHelper.getExpirationDateFromToken(token);
        JwtResponce jwtResponce = new JwtResponce() ;
        jwtResponce.setToken(token);
        jwtResponce.setValidTill(tokenexp);
        return new ResponseEntity<>(jwtResponce, HttpStatus.OK);

    }

    private void authanticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    }
}
