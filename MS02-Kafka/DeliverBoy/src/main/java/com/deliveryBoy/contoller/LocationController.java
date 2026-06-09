package com.deliveryBoy.contoller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryBoy.DTO.LocationDTO;
import com.deliveryBoy.service.KafkaProducerService;

@RestController
@RequestMapping("/location")
public class LocationController 
{
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@PostMapping("/send")
	public ResponseEntity<?> sendLocation(@RequestBody LocationDTO locationDTO)
	{
		/*
		this.kafkaProducerService.sendLocation(
				"(" + Math.round(Math.random()*100) + " , " + Math.round(Math.random()*100) + ")");
		*/
		
		this.kafkaProducerService.sendLocation(locationDTO);
		
		return new ResponseEntity<>(Map.of("message","Location sent to Kafka successfully"), HttpStatus.OK);
	}

}
