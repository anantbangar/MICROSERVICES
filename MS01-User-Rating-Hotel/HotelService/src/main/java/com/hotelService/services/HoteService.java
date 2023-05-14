package com.hotelService.services;

import java.util.List;

import com.hotelService.entities.Hotel;

public interface HoteService 
{
	Hotel createHotel(Hotel hotel);
	List<Hotel> getAllHotels();
	Hotel getHotel(String hotelId);

}
