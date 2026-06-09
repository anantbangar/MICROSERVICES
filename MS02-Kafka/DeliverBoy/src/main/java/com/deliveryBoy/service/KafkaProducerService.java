package com.deliveryBoy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.deliveryBoy.DTO.LocationDTO;
import com.deliveryBoy.config.AppConstants;

@Service
public class KafkaProducerService 
{
	@Autowired
	private KafkaTemplate<String, LocationDTO> kafkaTemplate;
	
	private Logger logger=LoggerFactory.getLogger(KafkaProducerService.class);
	
	public boolean sendLocation(LocationDTO locationDTO)
	{
		this.kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, locationDTO.getDriverId().toString(),locationDTO);
		this.logger.info("Message Produced");
		return true;
	}

}
