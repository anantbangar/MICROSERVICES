package com.ratingService.services;

import java.util.List;

import com.ratingService.entities.Rating;

public interface RatingService 
{
	Rating createRating(Rating rating);
	List<Rating> getAllRatings();
	List<Rating> getRatingsByUserId(String userId);
	List<Rating> getRatingsByHotelId(String hotelId);

}
