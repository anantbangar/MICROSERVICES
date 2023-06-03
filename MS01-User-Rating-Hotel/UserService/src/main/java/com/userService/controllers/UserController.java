package com.userService.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userService.entities.User;
import com.userService.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1 = userServiceImpl.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping
	@CircuitBreaker(name = "ratingHotelCircuitBreaker", fallbackMethod = "ratingHotelFallbackForAllUsers")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> allUsers = userServiceImpl.getAllUsers();
		return ResponseEntity.ok(allUsers);
		
	}	
	
	int retryCount=0;
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelCircuitBreaker", fallbackMethod = "ratingHotelFallbackForOneUser")
	@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallbackForOneUser")
	//@RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallbackForOneUser")
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId)
	{
		logger.info("Retry Count--- {}",retryCount);
		retryCount++;
		User user = userServiceImpl.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	
	//Fallback method for CircuitBreaker for All Users
	public ResponseEntity<List<User>> ratingHotelFallbackForAllUsers(Exception ex)
	{
		logger.info("Fallback is executed for All Users because service is down : {}",ex.getMessage());
		List<User> users=new ArrayList<User>();
		users.add(User.builder().userId("DummyId").userName("DummyUser").build());
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
			
	}	
	
	//Fallback method for CircuitBreaker for One User
	public ResponseEntity<User> ratingHotelFallbackForOneUser(String userId, Exception ex)
	{
		logger.info("Fallback is executed for One User because service is down : {}",ex.getMessage());
		User user = User.builder().userId("DummyId").userName("DummyName").build();
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}	
	
	/*
	@GetMapping("/byId")
	public ResponseEntity<User> getUser(@RequestParam("userId") String userId)
	{
		User user = userServiceImpl.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	*/
}
