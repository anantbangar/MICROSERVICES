package com.ratingService.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> exceptionOccurred(RuntimeException ex)
	{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("message", ex.getMessage());
		responseMap.put("success", false);
		responseMap.put("status", HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
	}

}
