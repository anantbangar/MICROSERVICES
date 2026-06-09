package com.endUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.endUser.service.LocationStorageService;

@Controller
public class LocationController 
{
	@Autowired
	private LocationStorageService locationStorageService;
	
	@GetMapping("/home")
	public String home(Model model)
	{
		model.addAttribute("location",this.locationStorageService);
		return "location";
	}
	
	@ResponseBody
    @GetMapping("/getLocation")
    public LocationStorageService getLocation() {

        return locationStorageService;
    }
}
