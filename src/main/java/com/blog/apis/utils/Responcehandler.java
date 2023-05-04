package com.blog.apis.utils;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.blog.apis.Enitys.Post;
import com.blog.apis.Enitys.User;
import com.blog.apis.playloads.PostDto;

public class Responcehandler {
	
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }

	public static PostDto generateResponse(String message, boolean b,Post save) {
		// TODO Auto-generated method stub
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", b);
        map.put("data", save);
		return null;
	}

	
		
	}

	
	
	
    
