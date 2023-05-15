package com.userService.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userService.entities.Hotel;
import com.userService.entities.Rating;
import com.userService.entities.User;
import com.userService.exceptions.ResourceNotFoundException;
import com.userService.repositories.UserRepository;
import com.userService.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) 
	{
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() 
	{
		List<User> userList = userRepository.findAll();

		List<User> users = new ArrayList<User>();

		List<Rating> ratings = new ArrayList<Rating>();

		for (User user : userList) 
		{
			Rating[] ratingsOfUser = restTemplate
					.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

			List<Rating> ratingsOfUserList = Arrays.asList(ratingsOfUser);

			if(!ratingsOfUserList.isEmpty())
			{
				for (Rating rating : ratingsOfUserList) 
				{
					Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
						Hotel.class);
					rating.setHotel(hotel);
					ratings.add(rating);
				}
				user.setRatings(ratings);
			}
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUser(String userId) 
	{
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with giving id is not found on Server !!"));

		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);

		List<Rating> ratings = Arrays.asList(ratingsOfUser);

		List<Rating> ratingList = ratings.stream().map(rating -> {

			Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			rating.setHotel(hotel);

			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;

		// return userRepository.findById(userId).get();

	}

}
