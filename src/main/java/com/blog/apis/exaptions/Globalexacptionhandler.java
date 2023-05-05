package com.blog.apis.exaptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blog.apis.utils.Responcehandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
			Apiresponce apiresponce = new Apiresponce(msg,false);
			return new ResponseEntity<Apiresponce>(apiresponce,HttpStatus.NOT_FOUND);
	   }
	
	
	@ExceptionHandler(BadCredentialsException.class)
	   public ResponseEntity<Object> Apibadcredexaption (BadCredentialsException exaption)
	   {
			return  Responcehandler.generateResponse("Invalid Username Or Password ! Try Again...",HttpStatus.BAD_REQUEST,null);
	   }

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> Samedatafound(SQLIntegrityConstraintViolationException exaption)
	{
		String msg = exaption.getMessage();
		return  Responcehandler.generateResponse(msg,HttpStatus.BAD_REQUEST,null);
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
