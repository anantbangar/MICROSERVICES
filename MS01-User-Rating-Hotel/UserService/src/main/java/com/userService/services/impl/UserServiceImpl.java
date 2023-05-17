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
import com.userService.external.services.HotelService;
import com.userService.external.services.RatingService;
import com.userService.repositories.UserRepository;
import com.userService.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	

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
			//Get Ratings using RestTemplate and removing Host and port
			Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

			List<Rating> ratingsOfUserList = Arrays.asList(ratingsOfUser);

			if(!ratingsOfUserList.isEmpty())
			{
				for (Rating rating : ratingsOfUserList) 
				{
					//Get Hotel using RestTemplate and removing Host and port
					Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
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

		//Get Ratings using RestTemplate and using http url
		//Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), Rating[].class);
		
		//Get Ratings using RestTemplate and removing Host and port
		//Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		
		//Get Ratings using Feign Client
		Rating[] ratingsOfUser = ratingService.getRatings(user.getUserId());
		

		List<Rating> ratings = Arrays.asList(ratingsOfUser);

		List<Rating> ratingList = ratings.stream().map(rating -> {

			//Get Hotel using RestTemplate and using http url
			//Hotel hotel = restTemplate.getForObject("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class);
			
			//Get Hotel using RestTemplate and removing Host and port
			//Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			
			//Get Hotel using Feign Client
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			rating.setHotel(hotel);

			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;

		// return userRepository.findById(userId).get();

	}

}
