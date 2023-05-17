package com.hotelService.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/staffs")
public class StaffsController 
{
	@GetMapping
	public ResponseEntity<List<String>> getStaffs()
	{
		List<String> staffList=Arrays.asList("Ram","Anant","Shailoo","Gulab");
		
		return ResponseEntity.ok(staffList);
	}

}
