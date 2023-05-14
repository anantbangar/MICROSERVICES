package com.ratingService.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingService.entities.Rating;
import com.ratingService.repositories.RatingRepository;
import com.ratingService.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService 
{
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) 
	{
		String randomRatingId = UUID.randomUUID().toString();
		rating.setRatingId(randomRatingId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() 
	{
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) 
	{
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) 
	{
		return ratingRepository.findByHotelId(hotelId);
	}

}
