package com.hotelService.services.impls;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelService.entities.Hotel;
import com.hotelService.exceptions.ResourceNotFoundException;
import com.hotelService.repositories.HotelRepository;
import com.hotelService.services.HoteService;

@Service
public class HotelServiceImpl implements HoteService 
{
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) 
	{
		String randomHotelId = UUID.randomUUID().toString();
		hotel.setHotelId(randomHotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() 
	{
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String hotelId) 
	{
		return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel is not found by this given Id !!"));
		
	}

}
