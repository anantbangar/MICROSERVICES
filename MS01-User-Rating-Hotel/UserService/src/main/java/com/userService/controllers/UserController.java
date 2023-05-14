package com.userService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userService.entities.User;
import com.userService.services.UserService;
import com.userService.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1 = userServiceImpl.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> allUsers = userServiceImpl.getAllUsers();
		return ResponseEntity.ok(allUsers);
		
	}
	
	/*
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId)
	{
		User user = userServiceImpl.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	*/
	
	@GetMapping("/byId")
	public ResponseEntity<User> getUser(@RequestParam("userId") String userId)
	{
		User user = userServiceImpl.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
}
