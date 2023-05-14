package com.userService.services;

import java.util.List;

import com.userService.entities.User;

public interface UserService 
{
	User createUser(User user);
	List<User> getAllUsers();
	User getUser(String userId);

}
