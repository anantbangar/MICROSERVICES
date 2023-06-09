package com.hotelService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelService.entities.Hotel;
import com.hotelService.services.impls.HotelServiceImpl;

@RestController
@RequestMapping("/hotels")
public class HotelController 
{
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel hotel2 = hotelServiceImpl.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel2);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels()
	{
		List<Hotel> allHotels = hotelServiceImpl.getAllHotels();
		return ResponseEntity.ok(allHotels);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") String hotelId)
	{
		Hotel hotel = hotelServiceImpl.getHotel(hotelId);
		return ResponseEntity.ok(hotel);
	}

}
