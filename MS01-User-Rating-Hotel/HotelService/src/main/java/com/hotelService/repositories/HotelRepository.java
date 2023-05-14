package com.hotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>
{

}
