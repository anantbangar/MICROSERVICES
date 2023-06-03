package com.userService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userService.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService 
{
	@GetMapping("/ratings/users/{userId}")
	Rating[] getRatings(@PathVariable("userId") String userId);

}
