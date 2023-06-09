package com.hotelService.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	/*
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlResourceNotFoundException(ResourceNotFoundException ex)
	{
		String msg = ex.getMessage();
		ApiResponse response = ApiResponse.builder().msg(msg).success(true).status(HttpStatus.NOT_FOUND).build();
	
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	*/
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlResourceNotFoundException(ResourceNotFoundException ex)
	{
		Map<String, Object> responseMap=new HashMap<String, Object>();
		responseMap.put("message", ex.getMessage());
		responseMap.put("success",false);
		responseMap.put("status", HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
		
	}

}
