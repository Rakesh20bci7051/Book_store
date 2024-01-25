package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.example.demo.payloads.ApiResponse;

@RestControllerAdvice //this annotaion handle all the exception of Controller
public class GlobalExceptionHandler {

	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>resouceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse obj=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse >(obj,HttpStatus.NOT_FOUND);
		
	}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
    {
    	Map<String, String>errors=new HashMap<>();
    	ex.getBindingResult().getAllErrors().forEach((error)->{
    		String fieldName=((FieldError)error).getField();
    		 String Message =error.getDefaultMessage();
    		 errors.put(fieldName, Message);
    	});
    	
    	return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
    }
	
	
	
	
}
