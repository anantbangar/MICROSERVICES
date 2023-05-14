package com.userService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userService.payload.ApiResponse;
import com.userService.payload.ApiResponse.ApiResponseBuilder;

@RestControllerAdvice
public class GlobalExceptionHandler 
{	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex)
	{
		String msg = ex.getMessage();
		ApiResponse response = ApiResponse.builder().msg(msg).success(true).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
		
	/*
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(Exception ex)
	{
		String msg = ex.getMessage();
		ApiResponse response = ApiResponse.builder().msg(msg).success(true).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	*/

}
