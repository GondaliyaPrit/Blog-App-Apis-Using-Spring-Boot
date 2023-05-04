package com.blog.apis.exaptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.apis.utils.Apiresponce;

@RestControllerAdvice
public class Globalexacptionhandler {

	@ExceptionHandler(ResourcenotfoundExaption.class)
	   public ResponseEntity<Apiresponce> resporcenotfoundexaption(ResourcenotfoundExaption exaption)
	   {
			String msg =exaption.getMessage();
			Apiresponce apiresponce = new Apiresponce(msg,true);
			return new ResponseEntity<Apiresponce>(apiresponce,HttpStatus.NOT_FOUND);
	   }
	 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>methodnotvalidexaption(MethodArgumentNotValidException ex)
	{
			Map<String,String> Errors = new HashMap<>();
		List<ObjectError>  list = 	ex.getBindingResult().getAllErrors();
		list.forEach(erorr->{
		String fieldname = ((FieldError)erorr).getField();
		String msg = erorr.getDefaultMessage();
		Errors.put(fieldname, msg);
		});
			return new ResponseEntity<Map<String,String>>(Errors,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
