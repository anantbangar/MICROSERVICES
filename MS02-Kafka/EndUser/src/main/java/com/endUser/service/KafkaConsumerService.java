package com.endUser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.endUser.DTO.LocationDTO;
import com.endUser.constants.AppConstants;

@Service
public class KafkaConsumerService 
{
	@Autowired
	private LocationStorageService locationStorageService;
	
	@KafkaListener(topics = AppConstants.LOCATION_TOPIC_NAME, groupId = AppConstants.GROUP_ID )
	public void receiveLocation(LocationDTO locationDTO)
	{
		System.out.println("Location Received");
		
		this.locationStorageService.setDriverId(locationDTO.getDriverId());
		this.locationStorageService.setLongitude(locationDTO.getLongitude());
		this.locationStorageService.setLatitude(locationDTO.getLatitude());
		this.locationStorageService.setTimestamp(locationDTO.getTimestamp());

        System.out.println("Driver Id : " + locationStorageService.getDriverId());
        System.out.println("Latitude  : " + locationStorageService.getLatitude());
        System.out.println("Longitude : " + locationStorageService.getLongitude());
        System.out.println("Time      : " + locationStorageService.getTimestamp());

        System.out.println("--------------------------------");
	}
	
}
